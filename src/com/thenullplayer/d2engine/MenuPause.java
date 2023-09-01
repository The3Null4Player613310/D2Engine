package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuPause extends Menu
{
	JButton continueButton;
	JButton opButton;
	JButton quitButton;

	public MenuPause(Window windowIn)
	{
		super(windowIn);

		this.setBackground(Color.green);
		
		continueButton = new JButton();
		continueButton.addActionListener(new ContinueListener(this));
		continueButton.setText("Continue");
		this.add(continueButton);

		opButton = new JButton();
		opButton.addActionListener(new OPListener(this));
		opButton.setText("Options");
		this.add(opButton);

		quitButton = new JButton();
		quitButton.addActionListener(new QuitListener(this));
		quitButton.setText("Quit");
		this.add(quitButton);
	}

	private class ContinueListener implements ActionListener
	{
		Menu menu;

		ContinueListener(Menu menuIn)
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
			//menu.hideMenu();
			menu.window.showOptions();
		}
	}

	private class QuitListener implements ActionListener
	{
		Menu menu;

		QuitListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.hideMenu();
			menu.window.hideScreen();
			menu.window.showMain();
		}
	}
}
