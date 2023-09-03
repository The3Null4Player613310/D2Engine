package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;

public class Sprite implements Point
{
	public final int SIZE = 16;
	public final int SCALE = 4;
	public final Color BG_COLOR = new Color(0,0,0,0);
	public final byte PALLET_MASK = 3;	

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
				int px = -posX * SCALE;
				int py = -posY * SCALE;

				gIn.setColor(pallet[sprite[i][j]]);
				gIn.fillRect(px+sx, py+sy, SCALE, SCALE);
			}
		}
	}
}
