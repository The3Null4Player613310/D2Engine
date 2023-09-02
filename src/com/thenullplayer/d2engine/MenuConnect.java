package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MenuConnect extends Menu
{

	JButton connectButton;
	JButton cancelButton;

	public MenuConnect(Window windowIn)
	{
		super(windowIn);
		this.init();
	}

	public MenuConnect(Window windowIn, Menu branchIn)
	{
		super(windowIn, branchIn);
		
		this.init();
	}

	private void init()
	{
		setBackground(Color.darkGray);

		connectButton = new JButton();
		connectButton.addActionListener(new ConnectListener(this));
		connectButton.setText("Connect");
		this.add(connectButton);

		cancelButton = new JButton();
		cancelButton.addActionListener(new CancelListener(this));
		cancelButton.setText("Cancel");
		this.add(cancelButton);
	}

	private class ConnectListener implements ActionListener
	{
		Menu menu;

		ConnectListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.closeMenuTree();
			menu.window.startGame();
		}
	}

	private class CancelListener implements ActionListener
	{
		Menu menu;

		CancelListener(Menu menuIn)
		{
			menu = menuIn;
		}

		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			menu.closeMenu();
		}
	}
}
