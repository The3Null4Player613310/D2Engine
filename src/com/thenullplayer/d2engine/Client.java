package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Client extends Thread implements Context
{
	private volatile boolean isRunning;
	private volatile boolean isConnected;

	private ManagerEntity entityManager;
	private Window window;
	private Keyboard keyboard;

	public Client()
	{
		entityManager = ManagerEntity.getInstance();
	
		window = new Window(this);
		keyboard = new Keyboard();
		keyboard.setListener(new KeyListener());
		window.addKeyListener(keyboard);
	}

	public int getContext()
	{
		return Context.CLIENT;
	}

	public void init()
	{
		isRunning = true;
		window.init();
		this.start();
	}

	public void run()
	{
		while(isRunning)
		{
			
		}
	}

	public void connect(InetAddress ip)
	{
	}

	public void pause()
	{
	}

	public void quit()
	{
		isRunning = false;
	}

	class KeyListener implements Keyboard.KeyboardListener
	{
		public void keyDown(int keyIn)
		{
			switch(keyIn)
			{
				case Keyboard.KEY_W:
				case Keyboard.KEY_S:
				case Keyboard.KEY_A:
				case Keyboard.KEY_D:
					window.repaint();
					break;
				case Keyboard.KEY_ESC:
					new MenuPause(window);
					break;
				default:
					System.out.println("" + keyIn + "DOWN");
					break;
			}
		}

		public void keyUp(int keyIn)
		{
			switch(keyIn)
			{
				default:
					System.out.println("" + keyIn + "UP");
					break;
			}
		}
	}
}
