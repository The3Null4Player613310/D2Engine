package com.thenullplayer.d2engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.PriorityQueue;

class ManagerRender
{
	public static final int ROWS = 10;
	public static final int COLS = 13;
	public static final int SIZE = 16;
	public static final int SCALE = 4;
	public static final int BATCH_SIZE = 8;

	private static ManagerRender manager;

	private Camera camera;

	//private int x = 0;
	//private int y = 0;

	//private int coordX = 0;
	//private int coordY = 0;
	
	private ArrayList<Tile> tileList;
	private ArrayList<Sprite> spriteList;
	private PriorityQueue<Tile> tileQueue;
	private PriorityQueue<Sprite> spriteQueue;
	private PriorityQueue<Sprite> spriteBuffer;

	Sprite[] sprite = new Sprite[BATCH_SIZE];
	Tile[][] tile = new Tile[ROWS][COLS];

	private ManagerRender()
	{
		camera = new Camera();
		camera.setPos(0,0); //-104, -80);

		tileList = new ArrayList<>();
		spriteList = new ArrayList<>();
		tileQueue = new PriorityQueue<Tile>((o1, o2) -> 
		{
			double m1 = Math.pow(Math.pow((o1.getX() - camera.getX()), 2) + Math.pow((o1.getY() - camera.getY()), 2), 0.5); 
			double m2 = Math.pow(Math.pow((o2.getX() - camera.getX()), 2) + Math.pow((o2.getY() - camera.getY()), 2), 0.5); 
			if(m1 > m2)
				return 1;
			else if(m1 < m2)
				return -1;
			else
				return 0; 
		});
		spriteQueue = new PriorityQueue<Sprite>((o1, o2) -> 
		{
			double m1 = Math.pow(Math.pow((o1.getX() - camera.getX()), 2) + Math.pow((o1.getY() - camera.getY()), 2), 0.5); 
			double m2 = Math.pow(Math.pow((o2.getX() - camera.getX()), 2) + Math.pow((o2.getY() - camera.getY()), 2), 0.5); 
			if(m1 > m2)
				return 1;
			else if(m1 < m2)
				return -1;
			else
				return 0; 
		});
		spriteBuffer = new PriorityQueue<Sprite>((o1, o2) -> 
		{
			double y1 = (o1.getY() - camera.getY()); 
			double y2 = (o2.getY() - camera.getY()); 
			if(y1 < y2)
				return 1;
			else if(y1 > y2)
				return -1;
			else
				return 0; 
		});
	}

	public void addSprite(Sprite spriteIn)
	{
		spriteList.add(spriteIn);
		spriteQueue.add(spriteIn);
	}

	public void removeSprite(Sprite spriteIn)
	{
		spriteList.remove(spriteIn);
	}

	public void addTile(Tile tileIn)
	{
		tileList.add(tileIn);
		tileQueue.add(tileIn);
	}

	public void removeTile(Tile tileIn)
	{
		tileList.remove(tileIn);
	}

	//private void addSprite(Sprite spriteIn)
	//{
	//	for(int i=0; i<(sprite.length-1); i++)
	//	{
	//		if(sprite[i] != null)
	//			sprite[i+1] = sprite[i];
	//	}
	//	sprite[0] = spriteIn;
	//}

	//private void removeSprite(Sprite spriteIn)
	//{
	//	for(int i=0; i<sprite.length; i++)
	//		if(sprite[i] == spriteIn)
	//			sprite[i] = null;
	//}

	//private boolean containsSprite(Sprite spriteIn)
	//{
	//	for(int i=0; i<sprite.length; i++)
	//		if(sprite[i] == spriteIn)
	//			return true;
	//	return false;
	//}

	public void follow(Entity entityIn)
	{
		camera.follow(entityIn);
	}

	public void think()
	{
		camera.think();

		Sprite[] sBatch = new Sprite[BATCH_SIZE];
		for(int i=0; i<sBatch.length; i++)
		{
			sBatch[i] = spriteQueue.poll();
		}

		for(int i=0; i<sBatch.length; i++)
		{
			if((sBatch[i] != null) && spriteList.contains(sBatch[i]))
			{
				spriteBuffer.add(sBatch[i]);
				spriteQueue.add(sBatch[i]);
			}
		}
		
		for(int i=0; i<BATCH_SIZE; i++)
		{
			sprite[i] = spriteBuffer.poll();			
		}

		Tile[][] tBatch = new Tile[ROWS][COLS];
		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				tBatch[i][j] = tileQueue.poll();
			}
		}
		
		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				if((tBatch[i] != null) && tileList.contains(tBatch[i][j]))
				{
					tile[i][j] = tBatch[i][j];
					tileQueue.add(tBatch[i][j]);
				}
			}
		}	

	}

	public void draw(Graphics gIn)
	{
		
		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				if(tile[i][j] != null)
					tile[i][j].draw(gIn, camera);
			}
		}

		for(int i=0; i<BATCH_SIZE; i++)
		{
			if(sprite[i] != null)
			{
				//sprite[i].setPos((int) (Math.random()*184), (int) (Math.random()*134));
				sprite[i].draw(gIn, camera);
			}
		}
	}

	public static ManagerRender getInstance()
	{
		if(manager == null)
			manager = new ManagerRender();
		return manager;
	}
}
