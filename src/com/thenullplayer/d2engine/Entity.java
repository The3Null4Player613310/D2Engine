package com.thenullplayer.d2engine;

import java.awt.Graphics;

public class Entity implements Point
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
	
	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}

	public void setPos(int posXIn, int posYIn)
	{
		posX = posXIn;
		posY = posYIn;
	}

	public void setVelocity(int velXIn, int velYIn)
	{
		velX = velXIn;
		velY = velYIn;
	}

	public void setSprite(Sprite spriteIn)
	{
		sprite = spriteIn;
	}

	public Sprite getSprite()
	{
		return sprite;
	}
}
