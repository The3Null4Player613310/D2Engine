package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel
{
	private boolean isVisible = true;

	Sprite[] sprite = new Sprite[8];

	public Screen(Dimension sizeIn)
	{
		super();


		hideScreen();
		this.setBackground(Color.gray);
		this.setPreferredSize(sizeIn);
		this.setMinimumSize(sizeIn);
		
		for(int i=0; i<8; i++)
			sprite[i] = new Sprite();

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
		
		for(int i=0; i<8; i++)
		{
			sprite[i].setPos((int) (Math.random()*400), (int) (Math.random()*400));
			sprite[i].draw(gIn);
		}
	}
}
