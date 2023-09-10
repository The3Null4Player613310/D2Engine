package com.thenullplayer.d2engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Song
{

	private boolean isLooped;
	private boolean isDone;

	private int index;
	private int[] song;

	public Song(File fileIn) throws Exception
	{
		if(!(fileIn.exists() && fileIn.isFile()))
			throw new Exception();
		
		load(fileIn);
	}

	public void setIsLooped(boolean isLoopedIn)
	{
		isLooped = isLoopedIn;
	}

	public boolean isLooped()
	{
		return isLooped;
	}

	public boolean isDone()
	{
		return isDone;
	} 

	private void load(File fileIn) throws FileNotFoundException
	{

		int size = getByteCount(fileIn);
		int[] data = new int[size];

		FileInputStream stream = new FileInputStream(fileIn);

		try
		{
			for(int i=0; i<data.length; i++)
			{
				data[i] = stream.read();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		

		try
		{
			stream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		

		song =  data;
	}

	private int getByteCount(File fileIn) throws FileNotFoundException
	{
		FileInputStream stream = new FileInputStream(fileIn);

		int count = -1;
		int data = 0;
		try
		{
			while(data != -1)
			{
				data = stream.read();
				count++;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			stream.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		
		return count;
	}

	public void reset()
	{
		index = 0;
		isDone = false;
	}

	public int getChord()
	{
		int chord = song[index];
		index = ((index+1) % song.length);
		if((isLooped == false) && (index == 0))
			isDone = true;
		return chord;
	}
}
