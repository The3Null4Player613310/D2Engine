package com.thenullplayer.d2engine;

public class AIWander implements AI
{
	private final int RANGE = 64;
	private final int IDLE_TIME = 300;

	boolean isSane;

	int posY;
	int posX;
	int vX;
	int vY;
	int idle;

	Entity entity;

	public AIWander(EntityLiving entityIn)
	{
		entity = entityIn;
	}

	public void setGoal(int posXIn, int posYIn)
	{
		posX = posXIn;
		posY = posYIn;
	}

	public void think()
	{
		
		int eX = entity.getX();
		int eY = entity.getY();

		if(!isSane)
		{
			setGoal(eX, eY);
			isSane=true;
		}

		if(idle > 0)
		{
			idle--;
			entity.setVelocity(0, 0);
			return;
		}

		if((posX == eX) &&(posY == eY))
		{
			posX += (int) RANGE * Math.random() - (RANGE/2);
			posY += (int) RANGE * Math.random() - (RANGE/2);
			idle += (int) (Math.random() * IDLE_TIME);
		}

		vX = 0;
		vX = (posX > eX)?  1 : vX;
		vX = (posX < eX)? -1 : vX;
		
		vY = 0;
		vY = (posY > eY)?  1 : vY;
		vY = (posY < eY)? -1 : vY;

		entity.setVelocity(vX, vY);
	}
}
