package com.thenullplayer.d2engine;

import java.io.File;

public class EntityMouse extends EntityAnimal
{
	public EntityMouse()
	{
		super();
		
		try
		{
			setSprite(new Sprite(new File(Sprite.DIR_SPRITE + "mouse.dat")));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
