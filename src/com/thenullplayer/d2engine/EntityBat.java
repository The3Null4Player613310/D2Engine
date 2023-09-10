package com.thenullplayer.d2engine;

import java.io.File;

public class EntityBat extends EntityAnimal
{
	
	public EntityBat()
	{
		super();

		setAI(new AIFly(this));
		
		try
		{
			setSprite(new Sprite(new File(Sprite.SPRITE_DIR + "bat.dat")));
		}
		catch(Exception e){}
	}
}
