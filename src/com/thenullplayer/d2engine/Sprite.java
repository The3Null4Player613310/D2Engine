package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;

public class Sprite implements Point
{
	public static final int SIZE = ManagerRender.SIZE; //16;
	public static final int SCALE = ManagerRender.SCALE; //4;
	public static final Color BG_COLOR = new Color(0,0,0,0);
	public static final byte PALLET_MASK = 3;	

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

	public void setPos(int posXIn, int posYIn)
	{
		posX=posXIn;
		posY=posYIn;
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
