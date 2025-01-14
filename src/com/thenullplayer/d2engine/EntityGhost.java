package com.thenullplayer.d2engine;

import java.io.File;

public class EntityGhost extends EntityMonster
{
	
	public EntityGhost()
	{
		super();

		setAI(new AIFloat(this));
		
		try
		{
			setSprite(new Sprite(new File(Sprite.DIR_SPRITE + "ghost.dat")));
		}
		catch(Exception e){}
	}
}
