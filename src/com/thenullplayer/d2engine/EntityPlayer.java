package com.thenullplayer.d2engine;

import java.awt.Color;

public class EntityPlayer extends EntityLiving
{

	final byte[][] SPRITE = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,3,3,3,3,0,0,0,0,0,0},
				{0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,0},
				{0,0,0,0,0,1,2,1,1,2,1,0,0,0,0,0},
				{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
				{0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,0},
				{0,0,0,0,3,0,3,3,3,3,0,3,0,0,0,0},
				{0,0,0,0,3,0,3,3,3,3,0,3,0,0,0,0},
				{0,0,0,0,1,0,3,3,3,3,0,1,0,0,0,0},
				{0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0},
				{0,0,0,0,0,2,2,0,0,2,2,0,0,0,0,0},
				{0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0},
				{0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0},
				{0,0,0,1,1,1,1,0,0,1,1,1,1,0,0,0}};

	public EntityPlayer()
	{
		super();

		int d = 128;
		int v = (int) (Math.random() * (256-d));
		Sprite sprite = new Sprite(SPRITE);
		sprite.setPrimary(new Color(v+d, v+(d/2), v));
		sprite.setSecondary(Color.blue);
		sprite.setTernary(new Color(255,0,0));
		setSprite(sprite);
	}

	public void think()
	{
		super.think();
		//System.out.println(""+getX()+","+getY());
	}
}
