package com.thenullplayer.d2engine;

import java.util.ArrayList;
import java.util.LinkedList;

public class ManagerEntity
{
	private final int BATCH_SIZE = 10;

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
		Entity curEntity = null;
		for(int i=0; i<BATCH_SIZE; i++)
		{
			curEntity = thinkQueue.poll();
			if((curEntity != null) && entityList.contains(curEntity))
			{	
				curEntity.think();
				thinkQueue.add(curEntity);
			}
		}
	}

	public static ManagerEntity getInstance()
	{
		if(manager == null)
			manager = new ManagerEntity();
		return manager;
	}
}
