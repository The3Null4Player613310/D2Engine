package com.thenullplayer.d2engine;

public class EntityLiving extends Entity
{
	public final int HEALTH_DEAD = 0;
	public final int HEALTH_FULL = 100;

	private boolean isAlive;
	private boolean isHurt;

	private int health; 

	private AI ai;

	public EntityLiving()
	{
		super();

		isAlive = true;
		health = 100;
	}

	public int getHealth()
	{
		return health;
	}

	public void setAI(AI aiIn)
	{
		ai = aiIn;
	}

	public void setHealth(int healthIn)
	{
		health = healthIn;
	}

	public boolean isAlive()
	{
		return isAlive;
	}

	public boolean isHurt()
	{
		return isHurt;
	}

	public void think()
	{
		if(ai != null)
			ai.think();

		super.think();

		isAlive = (health > HEALTH_DEAD);
		isHurt = (health < HEALTH_FULL);
	}
}
