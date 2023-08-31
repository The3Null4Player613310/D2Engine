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
	private Menu optionMenu;

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

		screen = new Screen(SIZE);
		this.add(screen);

		mainMenu = new MenuMain(this);
		this.add(mainMenu);
		mainMenu.showMenu();

		pauseMenu = new MenuPause(this);
		this.add(pauseMenu);

		optionMenu = new MenuOption(this);
		this.add(optionMenu);

		this.pack();
		this.setVisible(true);
	}

	private void hideMenus()
	{
		mainMenu.hideMenu();
		pauseMenu.hideMenu();
		optionMenu.hideMenu();
		screen.hideScreen();
	}

	public void showMain()
	{
		hideMenus();
		mainMenu.showMenu();
	}

	public void showScreen()
	{
		hideMenus();
		screen.showScreen();
	}

	public void showPause()
	{
		hideMenus();
		pauseMenu.showMenu();
	}

	public void showOptions()
	{
		hideMenus();
		optionMenu.showMenu();
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
