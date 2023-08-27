package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Menu extends JPanel
{
	private boolean isVisible;

	public Menu()
	{
		super();
		isVisible = false;

		this.setLayout(new SpringLayout());
	}

	public void hide()
	{
		if(isVisible)
		{
			isVisible = false;
			setVisible(isVisible);
		}
	}

	public void show()
	{
		if(!isVisible)
		{
			isVisible = true;
			setVisible(isVisible);
		}
	}

	public void toggle()
	{
		if(isVisible)
		{
			hide();
		}
		else
		{
			show();
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.RED);
	}
}
