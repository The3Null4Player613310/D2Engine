package com.thenullplayer.d2engine;

public class ManagerEntity
{
	private static ManagerEntity manager;

	private ManagerEntity()
	{
	}

	public static ManagerEntity getInstance()
	{
		if(manager == null)
			manager = new ManagerEntity();
		return manager;
	}
}
