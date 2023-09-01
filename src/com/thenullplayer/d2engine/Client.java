package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Client extends Thread implements Context
{
	private final int FPS = 15;
	private final long PTIME = 1000/FPS;

	private volatile boolean isRunning;
	private volatile boolean isConnected;
	private volatile boolean isPaused;

	private long startTime = 0;
	private long endTime = 0;
	private long delayTime = 0;

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
			while(isPaused)
			{
				try
				{
					Thread.sleep(800);
				}
				catch(Exception e){}
			}	
			startTime = System.currentTimeMillis();
			window.repaint();
			endTime = System.currentTimeMillis();
			delayTime = Math.max(PTIME - (endTime - startTime), 0);
			try
			{
				Thread.sleep(delayTime);
			}
			catch(Exception e){}
		}
	}

	public void connect(InetAddress ip)
	{
	}

	public void pause()
	{
		if(!isPaused)
		{
			isPaused = true;
			new MenuPause(window);
		}
	}

	public void play()
	{
		if(isPaused)
		{
			isPaused = false;
		}
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
					//window.repaint();
					break;
				case Keyboard.KEY_ESC:
					pause();
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
