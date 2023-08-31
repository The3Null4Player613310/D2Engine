package com.thenullplayer.d2engine;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionManager extends Thread implements Manager
{
	private static volatile ConnectionManager manager;

	private volatile boolean isRunning;
	private volatile ArrayList<Connection> clientList;

	private ConnectionManager()
	{
		clientList = new ArrayList<>();
		start();
	}

	public static ConnectionManager getInstance()
	{
		if(manager == null)
			manager = new ConnectionManager();
		return manager;			
	}

	public void run()
	{
		isRunning = true;
		while(isRunning)
			tick();
	}

	private void tick()
	{
		for(int i=0; i<clientList.size(); i++)
			clientList.get(i).tick();
	}

	public void create(Socket socketIn)
	{
		try
		{
			Connection connection = new Connection(socketIn);
			clientList.add(connection);
		}
		catch(Exception e){};
	}

	public void close()
	{
		isRunning = false;

		while(clientList.size() > 0)
			clientList.remove(0).close();

	}

	private class Connection
	{
		int id;
		Socket socket;
		
		InputStream input;
		OutputStream output;

		public Connection(Socket socketIn) throws Exception
		{
			socket = socketIn;

			socket.setKeepAlive(true);
			
			input = socket.getInputStream();
			output = socket.getOutputStream();
		}

		public void tick()
		{
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
