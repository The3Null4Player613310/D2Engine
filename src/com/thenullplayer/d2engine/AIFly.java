package com.thenullplayer.d2engine;

public class AIFly implements AI
{
	private final int RANGE = 64;
	private final int IDLE_TIME = 300;

	boolean isSane;


	long t = 0; 
	int posY;
	int posX;
	int vX;
	int vY;
	int idle;

	Entity entity;

	public AIFly(EntityLiving entityIn)
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

		t++;

		if(!isSane)
		{
			setGoal(eX, eY);
			isSane=true;
		}

		vX = 0;
		vY = 0;

		vY += (int) Math.round( Math.sin(t * (2 * Math.PI) / 22));

		if(idle > 0)
		{
			idle--;
					
			entity.setVelocity(vX, vY);
			return;
		}

		if((posX == eX) &&(posY == eY))
		{
			posX += (int) RANGE * Math.random() - (RANGE/2);
			posY += (int) RANGE * Math.random() - (RANGE/2);
			idle += (int) (Math.random() * IDLE_TIME);
		}

		vX = (posX > eX)?  1 : vX;
		vX = (posX < eX)? -1 : vX;
		
		vY = (posY > eY)?  1 : vY;
		vY = (posY < eY)? -1 : vY;

		entity.setVelocity(vX, vY);
	}
}
