package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuMain extends Menu
{
	JButton startButton;
	JButton spButton;
	JButton mpButton;

	public MenuMain(Window windowIn)
	{
		super(windowIn);

		this.setBackground(Color.red);

		startButton = new JButton();
		startButton.addActionListener(new ClickListener(this));
		startButton.setText("Start");
		this.add(startButton);


		spButton = new JButton();
		spButton.setText("Single-Player");
		this.add(spButton);

		mpButton = new JButton();
		mpButton.setText("Multi-Player");
		this.add(mpButton);
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
