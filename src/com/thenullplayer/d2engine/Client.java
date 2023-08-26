package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Client extends Thread
{
	private volatile boolean isRunning;

	private Window window;

	public Client()
	{
		window = new Window();
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

	public void quit()
	{
		isRunning = false;
	}
}
