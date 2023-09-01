package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;

public class Tile
{
	final int SIZE = 16;
	final int SCALE = 4;
	final Color BG_COLOR = new Color(0,0,0,0);

	private int posX = 0;
	private int posY = 0;

	Color[] pallet = new Color[4];
	byte[][] tile = new byte[SIZE][SIZE];

	public Tile()
	{
		pallet[0] = BG_COLOR;
		pallet[1] = new Color(0,192,0);
		pallet[2] = new Color(0,255,0);
		pallet[3] = new Color(192,192,0);

		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if((int) (Math.random() * 2) == 0)
					tile[i][j] = 1;
				else
					tile[i][j] = 2;

				if(((int) (Math.random() * 32)) == 0)
					tile[i][j] = 3;

			}		
		}
	}

	public void setPos(int posXIn, int posYIn)
	{
		posX = posXIn;
		posY = posYIn;
	}

	public void draw(Graphics gIn)
	{
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int cx = (j * SCALE) + posX * (SIZE * SCALE);
				int cy = (i * SCALE) + posY * (SIZE * SCALE);
				
				gIn.setColor(pallet[tile[i][j]]);
				gIn.fillRect(cx, cy, SCALE, SCALE);
			}
		}
	}
}
