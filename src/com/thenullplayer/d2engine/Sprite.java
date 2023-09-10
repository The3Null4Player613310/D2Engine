package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sprite implements Point
{
	public static final int SIZE = ManagerRender.SIZE; //16;
	public static final int SCALE = ManagerRender.SCALE; //4;
	public static final Color BG_COLOR = new Color(0,0,0,0);
	public static final byte PALLET_MASK = 3;
	
	public static final String SPRITE_DIR = Client.ASSET_DIR + File.separator + "sprite" + File.separator;

	private final int[] HEADER_IDENT = {0x53, 0x50, 0x52};
	
	private static final int HEADER_START_IDENT = 1;
	private static final int HEADER_START_SIZE_X = 4;
	private static final int HEADER_START_SIZE_Y = 5;
	private static final int HEADER_START_PALLET_PRIMARY = 6;
	private static final int HEADER_START_PALLET_SECONDARY = 9;
	private static final int HEADER_START_PALLET_TERNARY = 12;
	private static final int HEAD_PALLET_LENGTH = 3;

	private int posX=0;
	private int posY=0;

	private Color[] pallet = new Color[4];
	private byte[][] sprite = new byte[SIZE][SIZE]; 
	
	public Sprite()
	{
		pallet[0] = BG_COLOR;
		
		for(int i=1; i<pallet.length; i++)
		{
			int r = (int) Math.floor(Math.random()*256);
			int g = (int) Math.floor(Math.random()*256);
			int b = (int) Math.floor(Math.random()*256);
			pallet[i] = new Color(r, g, b);
		}

		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				sprite[i][j] = (byte) (Math.random() * 4);
			}
		}
	}

	public Sprite(byte[][] dataIn)
	{
		this();
		int row = Math.min(dataIn.length, SIZE);
		for(int i=0; i<row; i++)
		{
			int col = Math.min(dataIn[i].length, SIZE);
			for(int j=0; j<col; j++)
			{
				sprite[i][j] = (byte) (dataIn[i][j] & PALLET_MASK);
			}
		}
	}

	public Sprite(File fileIn) throws Exception
	{
		this();
		if(!(fileIn.exists() && fileIn.isFile()))
			throw new Exception();

		load(fileIn);
	}

	public void setPos(int posXIn, int posYIn)
	{
		posX = posXIn;
		posY = posYIn;
	}

	public void setPrimary(Color colorIn)
	{
		pallet[1] = colorIn;
	}

	public void setSecondary(Color colorIn)
	{
		pallet[2] = colorIn;
	}

	public void setTernary(Color colorIn)
	{
		pallet[3] = colorIn;
	}

	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}

	private void load(File fileIn) throws Exception
	{
		FileInputStream stream = new FileInputStream(fileIn);
		
		try
		{
			int headerSize = stream.read();
			int[] header = new int[headerSize];
			if(headerSize >= 0x10)
				header[0] = headerSize;
			else
				throw new Exception();
			
			for(int i=1; i<header[0]; i++)
			{
				header[i] = stream.read();
				if((i == 1) && (header[i] != HEADER_IDENT[0]))
					throw new Exception();
				if((i == 2) && (header[i] != HEADER_IDENT[1]))
					throw new Exception();
				if((i == 3) && (header[i] != HEADER_IDENT[2]))
					throw new Exception();
				if((i == 4) && (header[i] != 0x10))
					throw new Exception();
				if((i == 5) && (header[i] != 0x10))
					throw new Exception();
				if((i == (header[0]-1)) && (header[i] != 0xFF))
					throw new Exception();
			}

			setPrimary(new Color(header[6], header[7], header[8]));
			setSecondary(new Color(header[9], header[10], header[11]));
			setTernary(new Color(header[12], header[13], header[14]));

			byte[][] data = new byte[header[4]][header[5]];
			for(int i=0; i < header[4]; i++)
			{
				for(int j=0; j < header[5]; j++)
				{
					data[i][j] = (byte) (stream.read() & 0xFF);
				}
			}

			for(int i=0; i<data.length; i++)
			{
				for(int j=0; j<data.length; j++)
				{
					sprite[i][j] = data[i][j];
				}
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
	}
	
	public void draw(Graphics gIn)
	{
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int sx = j * SCALE;
				int sy = i * SCALE;
				int px = posX * SCALE;
				int py = -posY * SCALE;

				gIn.setColor(pallet[sprite[i][j]]);
				gIn.fillRect(px+sx, py+sy, SCALE, SCALE);
			}
		}
	}

	public void draw(Graphics gIn, Camera cameraIn)
	{
		int cameraX = cameraIn.getX();
		int cameraY = cameraIn.getY();
		
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int sx = j * SCALE;
				int sy = i * SCALE;
				int px = (posX * SCALE) - (cameraX * SCALE);
				int py = -(posY * SCALE) + (cameraY * SCALE);

				gIn.setColor(pallet[sprite[i][j]]);
				gIn.fillRect(px+sx, py+sy, SCALE, SCALE);
			}
		}
	}
}
