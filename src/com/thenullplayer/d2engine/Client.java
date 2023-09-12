package com.thenullplayer.d2engine;

import java.io.File;
import java.net.InetAddress;

public class Client extends Thread implements Context
{
	private static final int FPS = 45;//15;
	private static final long PTIME = 1000/FPS;

	private static final int MASK_M = 15;
	private static final int FLAG_U = 1;
	private static final int FLAG_D = 2;
	private static final int FLAG_L = 4;
	private static final int FLAG_R = 8;

	private static final String DIR_MAIN  = System.getProperty("user.dir") + File.separator;
	public static final String DIR_ASSET  = DIR_MAIN  + "assets" + File.separator; 
	public static final String DIR_SOUND  = DIR_ASSET + "sound"  + File.separator;
	public static final String DIR_MUSIC  = DIR_SOUND + "music"  + File.separator;
	public static final String DIR_MAP    = DIR_ASSET + "map"    + File.separator;
	public static final String DIR_SPRITE = DIR_ASSET + "sprite" + File.separator;
	public static final String DIR_TILE   = DIR_ASSET + "tile"   + File.separator;

	private static Window window;
	private static Client client;
	private static Keyboard keyboard;

	private volatile boolean isRunning;
	private volatile boolean isConnected;
	private volatile boolean isPaused;

	private long startTime = 0;
	private long endTime = 0;
	private long delayTime = 0;

	private int moveFlags;

	private ManagerEntity entityManager;
	private ManagerRender renderManager;
	private ManagerSound soundManager;

	private Entity player;

	private Client()
	{
		init();
	}

	private  void init()
	{
		bind();

		isPaused = true;

		keyboard.setListener(new KeyListener());

		entityManager = ManagerEntity.getInstance();
		renderManager = ManagerRender.getInstance();
		soundManager = ManagerSound.getInstance();

		player = new EntityPlayer();
		//entity.setVelocity(1,1);
		player.setPos(85,56);
		entityManager.addEntity(player);
		renderManager.follow(player);
		renderManager.addSprite(player.getSprite());

		for(int i=0; i<1; i++)
		{
			EntityAnimal animal = new EntityAnimal();
			animal.setPos((int) (Math.random()*200), (int) (Math.random()*-150));
			entityManager.addEntity(animal);
			renderManager.addSprite(animal.getSprite());
		}

		for(int i=0; i<1; i++)
		{
			EntityCat cat = new EntityCat();
			cat.setPos((int) (Math.random()*200), (int) (Math.random()*-150));
			entityManager.addEntity(cat);
			renderManager.addSprite(cat.getSprite());
		}

		for(int i=0; i<1; i++)
		{
			EntityBat bat = new EntityBat();
			bat.setPos((int) (Math.random()*200), (int) (Math.random()*-150));
			entityManager.addEntity(bat);
			renderManager.addSprite(bat.getSprite());
		}

		for(int i=0; i<10; i++)
		{
			EntityZombie zombie = new EntityZombie();
			zombie.setPos((int) (Math.random()*25*16), (int) (Math.random()*25*16));
			entityManager.addEntity(zombie);
			renderManager.addSprite(zombie.getSprite());
		}

		//for(int i=0; i<10; i++)
		//{
		//	for(int j=0; j<13; j++)
		//	{
		//		Tile tile = new Tile();
		//		tile.setPos(j, i-9);
		//		renderManager.addTile(tile);
		//	}
		//}
		this.start();
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
		soundManager.think();

		//System.out.println(moveFlags);

		var x = 0;
		var y = 0;
		if((moveFlags & FLAG_U) == FLAG_U)
			y+=1;
		if((moveFlags & FLAG_D) == FLAG_D)
			y-=1;
		if((moveFlags & FLAG_L) == FLAG_L)
			x-=1;
		if((moveFlags & FLAG_R) == FLAG_R)
			x+=1;
		player.setVelocity(x, y);
	}

	public void destroy()
	{
		unbind();

		entityManager = null;

		isPaused = false;
		isRunning = false;
		client = null;
	}

	public void connect(InetAddress ip)
	{
	}

	public void startGame()
	{
		if(isPaused)
		{
			isPaused = false;
		}
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
		if(!isPaused)
		{
			isPaused = true;
		}
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
			client = new Client();;
		}
	}

	public static void quitClient()
	{
		if(client != null)
			client.destroy();
	}

	class KeyListener implements Keyboard.KeyboardListener
	{
		public void keyDown(int keyIn)
		{
			switch(keyIn)
			{
				case Keyboard.KEY_W:
					moveFlags |= FLAG_U;
					break;
				case Keyboard.KEY_S:
					moveFlags |= FLAG_D;
					break;
				case Keyboard.KEY_A:
					moveFlags |= FLAG_L;
					break;
				case Keyboard.KEY_D:
					moveFlags |= FLAG_R;
					break;
				case Keyboard.KEY_E:
					soundManager.tone(440, 500);
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
				case Keyboard.KEY_W:
					moveFlags &= (~FLAG_U & MASK_M);
					break;
				case Keyboard.KEY_S:
					moveFlags &= (~FLAG_D & MASK_M);
					break;
				case Keyboard.KEY_A:
					moveFlags &= (~FLAG_L & MASK_M);
					break;
				case Keyboard.KEY_D:
					moveFlags &= (~FLAG_R & MASK_M);
					break;
				default:
					System.out.println("" + keyIn + " UP");
					break;
			}
		}
	}
}
