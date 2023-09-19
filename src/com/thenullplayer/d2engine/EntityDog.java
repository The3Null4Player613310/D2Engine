package com.thenullplayer.d2engine;

import java.io.File;

public class EntityDog extends EntityAnimal
{
	public EntityDog()
	{
		super();
		
		try
		{
			setSprite(new Sprite(new File(Sprite.DIR_SPRITE + "dog.dat")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
