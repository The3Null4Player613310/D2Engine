package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuMain extends Menu
{
	JButton startButton;

	public MenuMain(Window windowIn)
	{
		super(windowIn);

		this.setBackground(Color.red);

		startButton = new JButton();
		startButton.addActionListener(new ClickListener(this));
		startButton.setText("Start");
		this.add(startButton);
	}

	private class ClickListener implements ActionListener
	{
		Menu menu;

		ClickListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.hideMenu();
			menu.window.showScreen();
		}
	}
}
