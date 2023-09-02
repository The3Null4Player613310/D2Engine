package com.thenullplayer.d2engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.PriorityQueue;

class ManagerRender
{
	private final int ROWS = 10;
	private final int COLS = 13;
	private final int BATCH_SIZE = 8;

	private static ManagerRender manager;

	private ArrayList<Tile> tileList;
	private ArrayList<Sprite> spriteList;
	private PriorityQueue<Tile> tileQueue;
	private PriorityQueue<Sprite> spriteQueue;

	Sprite[] sprite = new Sprite[BATCH_SIZE];
	Tile[][] tile = new Tile[ROWS][COLS];

	private ManagerRender()
	{
		tileList = new ArrayList<>();
		spriteList = new ArrayList<>();
		tileQueue = new PriorityQueue<Tile>((o1,o2) -> { return 0; });
		spriteQueue = new PriorityQueue<Sprite>((o1,o2) -> { return 0; });
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
	}

	public void removeTile(Tile tileIn)
	{
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


	public void think()
	{
		Sprite[] batch = new Sprite[BATCH_SIZE];
		for(int i=0; i<batch.length; i++)
		{
			batch[i] = spriteQueue.poll();
		}

		for(int i=0; i<batch.length; i++)
		{
			if((batch[i] != null) && spriteList.contains(batch[i]))
			{
				sprite[i] = batch[i];
				spriteQueue.add(batch[i]);
			}
		}
	}

	public void draw(Graphics gIn)
	{
		gIn.drawLine(0,0,100,100);
		
		for(int i=0; i<ROWS; i++)
		{
			for(int j=0; j<COLS; j++)
			{
				if(tile[i][j] != null)
					tile[i][j].draw(gIn);
			}
		}

		for(int i=0; i<BATCH_SIZE; i++)
		{
			if(sprite[i] != null)
			{
				//sprite[i].setPos((int) (Math.random()*184), (int) (Math.random()*134));
				sprite[i].draw(gIn);
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
