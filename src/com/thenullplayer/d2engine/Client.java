package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Client extends Thread implements Context
{
	private volatile boolean isRunning;

	private Window window;
	private Keyboard keyboard;

	public Client()
	{
		window = new Window();
		keyboard = new Keyboard();
		window.addKeyListener(keyboard);
	}

	public int getContext()
	{
		return Context.CLIENT;
	}

	public void init()
	{
		isRunning = true;
		window.init(this);
		this.start();
	}

	public void run()
	{
		while(isRunning)
		{
			
		}
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
		}

		public void keyUp(int keyIn)
		{
		}
	}
}
