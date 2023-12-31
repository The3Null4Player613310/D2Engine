package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;

public class Tile implements Point
{
	final int SIZE = ManagerRender.SIZE;
	final int SCALE = ManagerRender.SCALE;
	final Color BG_COLOR = new Color(0,0,0,0);

	private int resistance;

	private int posX = 0;
	private int posY = 0;

	Color[] pallet = new Color[4];
	byte[][] tile = new byte[SIZE][SIZE];

	public Tile()
	{
		pallet[0] = BG_COLOR;
		setPrimary(new Color(0, 192, 0));
		setSecondary(new Color(0, 255, 0));
		setTernary(new Color(192, 192, 0));

		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				if((int) (Math.random() * 16) == 0)
					tile[i][j] = 1;
				else
					tile[i][j] = 2;

				if(((int) (Math.random() * 256)) == 0)
					tile[i][j] = 3;
			}		
		}
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

	public void setPos(int posXIn, int posYIn)
	{
		posX = posXIn;
		posY = posYIn;
	}

	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}

	public void setResistance(int resistanceIn)
	{
		resistance = resistanceIn;
	}

	public int getResistance()
	{
		return resistance;
	} 

	public void draw(Graphics gIn)
	{
		for(int i=0; i<SIZE; i++)
		{
			for(int j=0; j<SIZE; j++)
			{
				int cx = (j * SCALE) + posX * (SIZE * SCALE);
				int cy = (i * SCALE) + -posY * (SIZE * SCALE);
				
				gIn.setColor(pallet[tile[i][j]]);
				gIn.fillRect(cx, cy, SCALE, SCALE);
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
				int cx = (j * SCALE) + (posX * (SIZE * SCALE) - (cameraX * SCALE));
				int cy = (i * SCALE) + -(posY * (SIZE * SCALE) - (cameraY * SCALE));
				
				gIn.setColor(pallet[tile[i][j]]);
				gIn.fillRect(cx, cy, SCALE, SCALE);
			}
		}
	}
}
