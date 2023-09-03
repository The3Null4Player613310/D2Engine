package  com.thenullplayer.d2engine;

public class Camera implements Point
{
	
	private int posX;
	private int posY;
	private int velX;
	private int velY;
	
	public Camera()
	{
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

	public int getX()
	{
		return posX;
	}

	public int getY()
	{
		return posY;
	}

	public void think()
	{
		posX+=velX;
		posY+=velY;
	}
}
