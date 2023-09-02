package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel
{

	private boolean isVisible = true;

	private ManagerRender renderManager; 


	public Screen(Dimension sizeIn)
	{
		super();

		renderManager = ManagerRender.getInstance();

		hideScreen();
		
		this.setBackground(Color.gray);
		this.setPreferredSize(sizeIn);
		this.setMinimumSize(sizeIn);
		
		for(int i=0; i<8; i++)
			renderManager.addSprite(new Sprite());

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
		renderManager.draw(gIn);
	}
}
