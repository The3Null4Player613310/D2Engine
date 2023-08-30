package com.thenullplayer.d2engine;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame
{
	private final int WIDTH = 500; 
	private final int HEIGHT = 500;
	private final String TITLE = "D2Engine";
	public final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	private Client client;
	private Screen screen;
	private Menu mainMenu;
	private Menu pauseMenu;

	public Window()
	{
		super();
	}

	public void init(Client clientIn)
	{
		client = clientIn;
		
		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new CloseAdapter());

		mainMenu = new MenuMain(this);
		this.add(mainMenu);
		mainMenu.showMenu();

		pauseMenu = new MenuPause(this);
		this.add(pauseMenu);

		screen = new Screen(SIZE);
		this.add(screen);

		this.pack();
		this.setVisible(true);
	}

	public void showMain()
	{
		mainMenu.showMenu();
	}

	public void showScreen()
	{
		screen.showScreen();
	}

	public void showPause()
	{
		screen.hideScreen();
		pauseMenu.showMenu();
	}

	public void quit()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	private class CloseAdapter extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent eventIn)
		{
			client.quit();
		}
	}
}
