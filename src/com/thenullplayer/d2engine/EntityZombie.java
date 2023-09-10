package com.thenullplayer.d2engine;

import java.io.File;

public class EntityZombie extends EntityMonster
{

	public EntityZombie()
	{
		try
		{
			setSprite(new Sprite(new File(Sprite.SPRITE_DIR + "zombie.dat")));
		}
		catch(Exception e){}
	}
}
