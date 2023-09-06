package com.thenullplayer.d2engine;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;

public class ManagerSound extends Thread implements Manager
{
	public static final int FRAMES = 100;
	public static final int SAMPLE_RATE = 44100;
	public static final int CHANNEL_COUNT = 2;
	public static final int BITS_PER_SAMPLE = 16;
	public static final int SAMPLE_SIZE = (BITS_PER_SAMPLE / 8) * CHANNEL_COUNT;
	public static final int FRAME_SIZE = SAMPLE_SIZE * (SAMPLE_RATE / FRAMES);
	private static final AudioFormat FORMAT = new AudioFormat(SAMPLE_RATE, BITS_PER_SAMPLE, CHANNEL_COUNT, true, false);
	//private static final AudioFormat FORMAT = new AudioFormat(Encoding.PCM_FLOAT, SAMPLE_RATE, BITS_PER_SAMPLE, CHANNEL_COUNT, FRAME_SIZE, ((float) FRAMES), false);
 
	private static final int FLAG_F0 = 0x01;
	private static final int FLAG_F1 = 0x02;
	private static final int FLAG_F2 = 0x04;
	private static final int FLAG_F3 = 0x08;
	private static final int FLAG_F4 = 0x10;
	private static final int FLAG_F5 = 0x20;
	private static final int FLAG_F6 = 0x40;
	private static final int FLAG_F7 = 0x80;

	private static final int TEMPO = 100;
	private static final int F0 = 262;
	private static final int F1 = 294;
	private static final int F2 = 330;
	private static final int F3 = 349;
	private static final int F4 = 392;
	private static final int F5 = 440;
	private static final int F6 = 494;
	private static final int F7 = 0;

	private static final String DEFAULT = "default [default]";

	private static ManagerSound manager;

	private volatile boolean isRunning;

	private volatile int maxBufferSize;
	private volatile int minBufferSize;
	private volatile int bufferSize;
	private volatile int maxLines;
	private volatile Mixer mixer;
	private volatile SourceDataLine line;

	private int[] song;

	public ManagerSound()
	{
		Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
		
		for(int i=0; i<mixerInfo.length; i++)
		{
			if(mixerInfo[i].getName().equals(DEFAULT))
			{
				System.out.println("Getting Mixer: '"+mixerInfo[i].getName()+"'");
				mixer = AudioSystem.getMixer(mixerInfo[i]);
			}
		}

		if(mixer != null)
		{
			System.out.println("Finding Lines");
			Line.Info[] lineInfo = mixer.getSourceLineInfo();
			for(int i=0; i<lineInfo.length; i++)
				System.out.println(lineInfo[i]);


			maxLines = mixer.getMaxLines(lineInfo[0]);
			System.out.println("Max Lines: " + maxLines);
			
			try
			{
				line = (SourceDataLine) mixer.getLine(lineInfo[0]);
				line.addLineListener(new SoundListener());
				line.open();

				bufferSize = line.getBufferSize();
				System.out.println("Buffer Size: " + bufferSize);

				start();

			}catch(LineUnavailableException e)
			{
				e.printStackTrace();
			}
			
		}

	}

	public void run()
	{
		isRunning = true;

		//try
		//{
			byte[] data = new byte[bufferSize];

			int[] song = {
				0x04, 0x04, 0x08, 0x10, 
				0x10, 0x08, 0x04, 0x02, 
				0x01, 0x01, 0x02, 0x04,
				0x04, 0x02, 0x02,
				0x04, 0x04, 0x08, 0x10,
				0x10, 0x08, 0x04, 0x02,
				0x01, 0x01, 0x02, 0x04, 
				0x02, 0x01, 0x01, 
				0x02, 0x02, 0x04, 0x01,
				0x02, 0x08, 0x04, 0x01,
				0x02, 0x08, 0x04, 0x02,
				0x01, 0x02, 0x10, 
				0x04, 0x04, 0x08, 0x10, 
				0x10, 0x08, 0x04, 0x02,
				0x01, 0x01, 0x02, 0x04,
				0x02, 0x01, 0x01
				};

			long t = 0;
			int beat = 0;
			int chord = 0;
			while(isRunning)
			{
				for(int j=0; j<256; j++)
				{
					if((t % (60 * 2 * SAMPLE_RATE/TEMPO)) == 0)
					{
						chord = song[beat];
						beat++;
						beat%=song.length;
					}
						//chord = (int) (Byte.MAX_VALUE * Math.random());
			
					//t * ((double) 440 / SAMPLE_RATE) * (2 * Math.PI)
					t++;
			
					short val = 0;
			
					if((chord & FLAG_F0) == FLAG_F0)
						val += (short) (16 * Math.sin(t * ((double) F0 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F1) == FLAG_F1)
						val += (short) (16 * Math.sin(t * ((double) F1 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F2) == FLAG_F2)
						val += (short) (16 * Math.sin(t * ((double) F2 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F3) == FLAG_F3)
						val += (short) (16 * Math.sin(t * ((double) F3 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F4) == FLAG_F4)
						val += (short) (16 * Math.sin(t * ((double) F4 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F5) == FLAG_F5)
						val += (short) (16 * Math.sin(t * ((double) F5 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F6) == FLAG_F6)
						val += (short) (16 * Math.sin(t * ((double) F6 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					if((chord & FLAG_F7) == FLAG_F7)
						val += (short) (16 * Math.sin(t * ((double) F7 * 2 * Math.PI) / (CHANNEL_COUNT * SAMPLE_RATE)));
					
					byte min = (byte) (val & 0xFF); 
					byte maj = (byte) ((val >>> 8) & 0xFF);

					for(int i=0; i<CHANNEL_COUNT; i++)
					{
						data[j+(CHANNEL_COUNT*i)+0] = min;
						data[j+(CHANNEL_COUNT*i)+1] = maj;
					}
				}
				line.write(data, 0, 256);
				if(!line.isRunning())
					line.start();
				//line.drain();
			}
		//}
		//catch(LineUnavailableException e)
		//{
		//	e.printStackTrace();
		//}

		if(line != null)
		{
			line.close();
		}
	}

	public void think()
	{
	}

	public void play()
	{
	
		//try
		//{
			String fpath = System.getProperty("user.dir") + "/data/sounds"; 
			System.out.println(fpath);
			File file = new File(fpath+"/horse.wav");
			try
			{
				//FileInputStream fis = new FileInputStream(file);
				//AudioFormat format = new AudioFormat(Encoding.PCM_UNSIGNED, SAMPLE_RATE, 32, 2, FRAME_SIZE, ((float) SAMPLE_RATE)/FRAME_SIZE, true); 
				//AudioInputStream stream = new AudioInputStream(fis, format, 1000);
				AudioInputStream stream = AudioSystem.getAudioInputStream(file); 
				Clip clip = AudioSystem.getClip();
				try
				{
					clip.open(stream);
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				clip.start();
				try
				{
					Thread.sleep(1500);
				}
				catch(Exception e){};
				clip.close();
			}
			catch(Exception e){};
		//}
		//catch(LineUnavailableException e)
		//{
		//	e.printStackTrace();
		//}
	}

	public void tone(int frequencyIn, int durationIn)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			int sampleSize = (int) Math.ceil((float)BITS_PER_SAMPLE/8);
			int size = durationIn * SAMPLE_RATE * CHANNEL_COUNT * sampleSize / 1000;
			byte[] buffer = new byte[size];
			for(int i=0; i<((SAMPLE_RATE * durationIn) / 1000); i++)
			{
				int sample = i * sampleSize * CHANNEL_COUNT;
				Short val = (short) (Short.MAX_VALUE/2 * Math.sin(i * ((2 * Math.PI) / ((double) SAMPLE_RATE / frequencyIn))));
				for(int j=0; j<sampleSize; j++)
				{
					buffer[(i * CHANNEL_COUNT * sampleSize) + j] = (byte) ((val >> (8*j)) & 0xFF);

					//System.out.println(buffer[(i*CHANNEL_COUNT) + j]);
				}
			}
			ByteArrayInputStream bStream = new ByteArrayInputStream(buffer);
			AudioInputStream aStream = new AudioInputStream(bStream, FORMAT, (SAMPLE_RATE * durationIn) / 1000);
			try
			{
				clip.addLineListener(new ToneListener(clip));
				clip.open(aStream);
				clip.start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(LineUnavailableException e)
		{
			e.printStackTrace();
		}

	}

	private class SoundStream extends InputStream
	{
		final int FREQUENCY = 440;

		public SoundStream()
		{
		}

		long offset = 0;
		long sample = 0;
		@Override
		public int read()
		{
			int v = (int) (127 * Math.sin(((double) sample * FREQUENCY * (2 * Math.PI) / SAMPLE_RATE))); 
			int b = 0xFF & (v >>> (8 * (3 - (offset % 4))));
			if(offset == 3)
			{
				sample++;
				//System.out.print(""+ v + " ");
			}
			else
			{
				//System.out.print("00");
			}
			offset = (offset+1)%4;
			return b;
		}
	}

	private class SoundListener implements LineListener
	{
		private final LineEvent.Type OPEN = LineEvent.Type.OPEN;	
		private final LineEvent.Type START = LineEvent.Type.START;	
		private final LineEvent.Type STOP = LineEvent.Type.STOP;	
		private final LineEvent.Type CLOSE = LineEvent.Type.CLOSE;	

		public SoundListener()
		{
		}

		public void update(LineEvent eventIn)
		{
			if(eventIn.getType() == OPEN)
			{
				System.out.println("Line Opened");
			}
			else if(eventIn.getType() == START)
			{
				System.out.println("Line Started");
			}
			else if(eventIn.getType() == STOP)
			{
				System.out.println("Line Stoped");
			}
			else if(eventIn.getType() == CLOSE)
			{
				System.out.println("Line Closed");
			}
		}
	}

	private class ToneListener implements LineListener
	{
		private final LineEvent.Type OPEN = LineEvent.Type.OPEN;	
		private final LineEvent.Type START = LineEvent.Type.START;	
		private final LineEvent.Type STOP = LineEvent.Type.STOP;	
		private final LineEvent.Type CLOSE = LineEvent.Type.CLOSE;	

		Clip clip;

		public ToneListener(Clip clipIn)
		{
			clip = clipIn;
		}

		public void update(LineEvent eventIn)
		{
			if(eventIn.getType() == STOP)
				clip.close();
		}
	}

	public static ManagerSound getInstance()
	{
		if(manager == null)
			manager = new ManagerSound();
		return manager;
	}
}
