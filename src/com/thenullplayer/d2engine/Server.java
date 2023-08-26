package com.thenullplayer.d2engine;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread
{
	private final int TIMEOUT = 4000;

	private volatile boolean isRunning;

	private volatile ServerSocket server;
	private volatile ArrayList<Connection> clientList;

	public Server()
	{
	}

	public void init(int portIn)
	{
		isRunning = true;
		clientList = new ArrayList<>();

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
				clientList.add(new Connection(socket));
			}
			catch(Exception e){}
		}
		
		for(int i=0; i<clientList.size(); i++)
		{
			clientList.get(i).close();
		}
		
		try
		{
			server.close();
		}catch(IOException e){}
	}

	public void quit()
	{
		isRunning = false;
	}

	private class Connection
	{
		int id;
		Socket socket;

		public Connection(Socket socketIn)
		{
			socket = socketIn;
		}

		public void close()
		{
			try
			{
				socket.close();
			}catch(IOException e){}
		}
	}
}
