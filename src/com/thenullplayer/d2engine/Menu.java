package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Menu extends JPanel
{
	private boolean isVisible = true;
	public final Window window;

	public Menu(Window windowIn)
	{
		super();

		window = windowIn;		

		this.hideMenu();
		this.setPreferredSize(window.SIZE);
		this.setMinimumSize(window.SIZE);

		this.setLayout(new SpringLayout());
	}

	public void hideMenu()
	{
		if(isVisible)
		{
			isVisible = false;
			this.setVisible(isVisible);
		}
	}

	public void showMenu()
	{
		if(!isVisible)
		{
			isVisible = true;
			this.setVisible(isVisible);
		}
	}

	public void toggleMenu()
	{
		if(isVisible)
		{
			hideMenu();
		}
		else
		{
			showMenu();
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}
