package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Main
{
	public final static int PORT = 8888;

	public boolean isRunning = true;

	public Client client;
	public Server server;


	public Main()
	{
	}

	public Main(boolean isServer)
	{
		if(isServer)
		{
			server = new Server();
			server.init(PORT);
		}
		else
		{
			client = new Client();
			client.init();
		}
		while(isRunning){};
	}

	public static void main(String[] args)
	{
		boolean isServer = false;
		for(int i=0; i<args.length; i++)
		{
			if(args[i] == "-S")
			{
				isServer = true;
			}
		}

		System.out.println("");
		new Main(isServer);
	}
}
