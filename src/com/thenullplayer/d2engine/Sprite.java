package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;

public class Sprite
{
	public final int SIZE = 16;
	public final int SCALE = 4;

	private int x=0;
	private int y=0;

	private Color[][] sprite = new Color[16][16];

	public Sprite()
	{
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int r = (int) Math.floor(Math.random()*256);
				int g = (int) Math.floor(Math.random()*256);
				int b = (int) Math.floor(Math.random()*256);
				sprite[j][i] = new Color(r, g, b);
			}
		}
	}

	public void setPos(int xIn, int yIn)
	{
		x=xIn;
		y=yIn;
	}

	public void draw(Graphics gIn)
	{
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int sx = j * SCALE;
				int sy = i * SCALE;

				gIn.setColor(sprite[i][j]);
				gIn.fillRect(x+sx, y+sy, SCALE, SCALE);
			}
		}
	}
}
