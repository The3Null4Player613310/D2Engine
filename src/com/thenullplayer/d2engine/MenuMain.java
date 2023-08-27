package com.thenullplayer.d2engine;

import javax.swing.JButton;

public class MenuMain extends Menu
{
	JButton startButton;

	public MenuMain()
	{
		super();

		startButton = new JButton();
		startButton.setText("Start");
		this.add(startButton);
	}
}
