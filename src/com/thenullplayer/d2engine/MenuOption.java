package com.thenullplayer.d2engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuOption extends Menu
{
	private JButton exitButton;

	public MenuOption(Window windowIn)
	{
		super(windowIn);

		exitButton = new JButton();
		exitButton.addActionListener(new ExitListener(this));
		exitButton.setText("Exit");
		this.add(exitButton);
	}

	private class ExitListener implements ActionListener
	{
		Menu menu;

		ExitListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.window.showMain();
		}
	}
}
