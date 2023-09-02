package com.thenullplayer.d2engine;

import java.net.InetAddress;

public class Client extends Thread implements Context
{
	private final int FPS = 15;//15;
	private final long PTIME = 1000/FPS;

	private static Window window;
	private static Client client;
	private static Keyboard keyboard;

	private volatile boolean isRunning;
	private volatile boolean isConnected;
	private volatile boolean isPaused;

	private long startTime = 0;
	private long endTime = 0;
	private long delayTime = 0;

	private ManagerEntity entityManager;
	private ManagerRender renderManager;

	private Client()
	{
		init();
	}

	private  void init()
	{
		bind();

		keyboard.setListener(new KeyListener());

		entityManager = ManagerEntity.getInstance();
		renderManager = ManagerRender.getInstance();

		Entity entity = new Entity();
		entity.setVelocity(1,1);
		entityManager.addEntity(entity);
		renderManager.addSprite(entity.getSprite());

	}

	private void bind()
	{
		window.bind(this);
	}

	private void unbind()
	{
		window.unbind(this);
	}

	public int getContext()
	{
		return Context.CLIENT;
	}

	public void run()
	{
		isRunning = true;
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
			think();
			window.repaint();
			endTime = System.currentTimeMillis();
			delayTime = Math.max(PTIME - (endTime - startTime), 0);
			try
			{
				Thread.sleep(delayTime);
			}
			catch(Exception e){}
		}
		this.destroy();
	}

	private void think()
	{
		entityManager.think();
		renderManager.think();
	}

	public void destroy()
	{
		unbind();

		entityManager = null;
	}

	public void connect(InetAddress ip)
	{
	}

	public void startGame()
	{
		startClient();
	}

	public void pauseGame()
	{
		if(!isPaused)
		{
			isPaused = true;
			new MenuPause(window);
		}
	}

	public void resumeGame()
	{
		if(isPaused)
		{
			isPaused = false;
		}
	}

	public void quitGame()
	{
		isPaused = false;
		isRunning = false;
		client = null;
	}

	public static void startClient()
	{
		if(keyboard == null)
		{
			keyboard = new Keyboard();
		}
		if(window == null)
		{
			window = new Window();
			window.addKeyListener(keyboard);
		}
		if(client == null)
		{
			client = new Client();
			client.start();
		}
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
					pauseGame();
					break;
				default:
					System.out.println("" + keyIn + " DOWN");
					break;
			}
		}

		public void keyUp(int keyIn)
		{
			switch(keyIn)
			{
				default:
					System.out.println("" + keyIn + " UP");
					break;
			}
		}
	}
}
