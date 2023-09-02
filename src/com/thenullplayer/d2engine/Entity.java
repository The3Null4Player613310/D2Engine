package com.thenullplayer.d2engine;

import java.awt.Graphics;

public class Entity
{
	private int posX = 0;
	private int posY = 0;
	private int velX = 0;
	private int velY = 0;

	private Sprite sprite;

	public Entity()
	{
		sprite = new Sprite();
	}

	private void move()
	{
		posX += velX;
		posY += velY;
	}

	public void think()
	{
		move();
		sprite.setPos(posX, posY);
	}

	public void draw(Graphics gIn)
	{
		sprite.draw(gIn);
	}

	public void setVelocity(int velXIn, int velYIn)
	{
		velX = velXIn;
		velY = velYIn;
	}

	public Sprite getSprite()
	{
		return sprite;
	}
}
