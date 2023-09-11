package com.thenullplayer.d2engine;

import java.io.File;

public class EntityCat extends EntityAnimal
{
	public EntityCat()
	{
		super();
		
		try
		{
			setSprite(new Sprite(new File(Sprite.DIR_SPRITE + "cat.dat")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
