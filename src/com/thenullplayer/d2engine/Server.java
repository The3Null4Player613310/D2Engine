package com.thenullplayer.d2engine;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread implements Context
{
	private final int TIMEOUT = 4000;

	private volatile boolean isRunning;

	private volatile ServerSocket server;
	private volatile ManagerConnection connectionManager;

	public Server()
	{
		connectionManager = ManagerConnection.getInstance();
	}

	public int getContext()
	{
		return Context.SERVER;
	}

	public void init(int portIn)
	{
		isRunning = true;

		boolean isReady = false;
		while(!isReady)
		{
			try
			{
				server = new ServerSocket(portIn);
				server.setSoTimeout(TIMEOUT);
				isReady = true;
			}
			catch(IOException e)
			{
				isReady = false;
				try
				{
					server.close();
				}
				catch(Exception f){}
			}
		}

		this.start();
	}

	public void run()
	{
		while(isRunning)
		{

			try
			{
				Socket socket = server.accept();
				connectionManager.create(socket);
			}
			catch(Exception e){}

		}

		connectionManager.close();
				
		try
		{
			server.close();
		}catch(IOException e){}
	}

	public void quit()
	{
		isRunning = false;
	}
}
