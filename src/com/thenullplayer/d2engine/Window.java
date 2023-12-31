package com.thenullplayer.d2engine;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class Window extends JFrame
{
	public static final int WIDTH = 800; 
	public static final int HEIGHT = 600;
	private static final String TITLE = "D2Engine";
	public static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
	
	private Client client;
	private SpringLayout layout;
	private Screen screen;
	private Menu mainMenu;
	private Menu pauseMenu;
	private Menu optionMenu;

	public Window()
	{
		super();

		layout = new SpringLayout();

		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(SIZE);
		this.setResizable(false);
		this.setLayout(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new CloseAdapter());

		mainMenu = new MenuMain(this);
		
		screen = new Screen(SIZE);
		this.add(screen);

		this.pack();
		this.setVisible(true);
	}

	public void bind(Client clientIn)
	{
		client = clientIn;
	}

	public void unbind(Client clientIn)
	{
		if(clientIn == client)
			client = null;
	}

	private void hideScreen()
	{
		screen.hideScreen();
	}

	private void showScreen()
	{
		screen.showScreen();
	}

	public void startGame()
	{
		if(client != null)
		{
			showScreen();
			client.startGame();
		}
	}

	public void pauseGame()
	{
		if(client != null)
		{
			client.pauseGame();
		}
	}

	public void resumeGame()
	{
		if(client != null)
		{
			showScreen();
			client.resumeGame();
		}
	}

	public void quitGame()
	{
		if(client != null)
		{
			hideScreen();
			client.quitGame();
		}
	}

	public void quit()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void addKeyListener(KeyListener listenerIn)
	{
		super.addKeyListener(listenerIn);
		screen.addKeyListener(listenerIn);
	}

	private void position(Component componentIn)
	{
		if(componentIn instanceof Menu)
		{
			layout.putConstraint(SpringLayout.NORTH, componentIn, 50, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.EAST, componentIn, -150, SpringLayout.EAST, this);
			layout.putConstraint(SpringLayout.SOUTH, componentIn, -50, SpringLayout.SOUTH, this);
			layout.putConstraint(SpringLayout.WEST, componentIn, 150, SpringLayout.WEST, this);
		}
		else if(componentIn instanceof Screen)
		{
			layout.putConstraint(SpringLayout.NORTH, componentIn, 0, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.EAST, componentIn, 0, SpringLayout.EAST, this);
			layout.putConstraint(SpringLayout.SOUTH, componentIn, 0, SpringLayout.SOUTH, this);
			layout.putConstraint(SpringLayout.WEST, componentIn, 0, SpringLayout.WEST, this);
		}
	}

	@Override
	public Component add(Component componentIn)
	{
		super.add(componentIn);
		position(componentIn);
		return componentIn;
	}

	@Override
	public Component add(Component componentIn, int indexIn)
	{
		super.add(componentIn, indexIn);
		position(componentIn);
		return componentIn;
	}

	private class CloseAdapter extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent eventIn)
		{
			if(client != null)
			{
				client.quitGame();
			}
		}
	}
}
