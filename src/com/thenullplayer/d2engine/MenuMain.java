package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuMain extends Menu
{
	JButton spButton;
	JButton mpButton;
	JButton opButton;

	public MenuMain(Window windowIn)
	{
		super(windowIn);

		this.setBackground(Color.blue);

		spButton = new JButton();
		spButton.addActionListener(new SPListener(this));
		spButton.setText("Single-Player");
		this.add(spButton);

		mpButton = new JButton();
		mpButton.addActionListener(new MPListener(this));
		mpButton.setText("Multi-Player");
		this.add(mpButton);

		opButton = new JButton();
		opButton.addActionListener(new OPListener(this));
		opButton.setText("Options");
		this.add(opButton);
	}

	private class SPListener implements ActionListener
	{
		Menu menu;

		SPListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.closeMenu();
			menu.window.play();
		}
	}

	private class MPListener implements ActionListener
	{
		Menu menu;

		MPListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.closeMenu();
			menu.window.showScreen();
		}
	}

	private class OPListener implements ActionListener
	{
		Menu menu;

		OPListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			new MenuOption(menu.window, menu);
		}
	}
}
