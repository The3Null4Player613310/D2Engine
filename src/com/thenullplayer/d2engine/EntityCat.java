package com.thenullplayer.d2engine;

import java.io.File;

public class EntityCat extends EntityAnimal
{
	public EntityCat()
	{
		super();
		
		try
		{
			setSprite(new Sprite(new File(Sprite.SPRITE_DIR + "cat.dat")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
