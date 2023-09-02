package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel
{
	private final int ROWS = 10;
	private final int COLS = 13;

	private boolean isVisible = true;

	Sprite[] sprite = new Sprite[8];
	Tile[][] tile = new Tile[ROWS][COLS];

	public Screen(Dimension sizeIn)
	{
		super();


		hideScreen();
		this.setBackground(Color.gray);
		this.setPreferredSize(sizeIn);
		this.setMinimumSize(sizeIn);
		
		for(int i=0; i<8; i++)
			sprite[i] = new Sprite();

		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				tile[i][j] = new Tile();
				tile[i][j].setPos(j, i);
			}
		}
	}

	public void hideScreen()
	{
		if(isVisible)
		{
			isVisible = false;
			setVisible(isVisible);
		}
	}

	public void showScreen()
	{
		if(!isVisible)
		{
			isVisible = true;
			setVisible(isVisible);
		}
		repaint();
		requestFocusInWindow();
	}

	@Override
	public void paint(Graphics gIn)
	{
		gIn.drawLine(0,0,100,100);
		
		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				tile[i][j].draw(gIn);
			}
		}

		for(int i=0; i<8; i++)
		{
			if(sprite[i] != null)
			{
				sprite[i].setPos((int) (Math.random()*184), (int) (Math.random()*134));
				sprite[i].draw(gIn);
			}
		}
	}
}
