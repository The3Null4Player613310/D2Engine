package com.thenullplayer.d2engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class ManagerEntity implements Manager
{
	private final int BATCH_SIZE = 64;

	private static ManagerEntity manager;

	private ArrayList<Entity> entityList;
	private LinkedList<Entity> thinkQueue;

	private ManagerEntity()
	{
		entityList = new ArrayList<>();
		thinkQueue = new LinkedList<>();
	}

	public void addEntity(Entity entityIn)
	{
		entityList.add(entityIn);
		thinkQueue.add(entityIn);
	}

	public void removeEntity(int indexIn)
	{
		entityList.remove(indexIn);
	}

	public void think()
	{
		Entity[] batch = new Entity[BATCH_SIZE]; 
		for(int i=0; i<BATCH_SIZE; i++)
		{
			batch[i] = thinkQueue.poll();
		}
	
		for(int i=0; i<BATCH_SIZE; i++)
		{
			if((batch[i] != null) && entityList.contains(batch[i]))
			{
				batch[i].think();
				thinkQueue.add(batch[i]);
			}
		}		
	}

	public void draw(Graphics gIn)
	{
		for(int i=0; i<entityList.size(); i++)
		{
			entityList.get(i).draw(gIn);
		}
	}

	public static ManagerEntity getInstance()
	{
		if(manager == null)
			manager = new ManagerEntity();
		return manager;
	}
}
