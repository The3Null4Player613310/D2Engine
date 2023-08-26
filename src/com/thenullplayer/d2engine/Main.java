package com.thenullplayer.d2engine;

public class Main
{
	public final int port = 8888;
	public final InetAddress localHost = InetAddress.getLocalHost();

	public boolean isRunnning = true;

	public Client client;
	public Server server;

	public Main(InetAddress ipIn)
	{
		if(ipIn == localHost)
		{
			server = new Server();
			server.init();
		}
		
		client = new Client();
		client.init(ipIn);
		
		while(isRunning){};
	}

	public static void main(String[] args)
	{
		for(int i=0; i<args.length; i++)
		{
			if(args[i] == "-S")
			{
			}
			else if(args[i] == "-H")
			{
			}
		}

		System.out.println("");
		new Main();
	}
}
