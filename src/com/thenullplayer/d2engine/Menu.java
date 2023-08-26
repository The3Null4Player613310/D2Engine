package com.thenullplayer.d2engine;

import javax.swing.JPanel;

public class Menu extends JPanel
{
	private boolean isVisible;

	public Menu()
	{
		super();
		isVisible = false;
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
}
