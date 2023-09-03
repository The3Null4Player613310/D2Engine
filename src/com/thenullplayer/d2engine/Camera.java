package  com.thenullplayer.d2engine;

public class Camera implements Point
{
	private static final int RANGE = 48;
	private static final int SIZE = ManagerRender.SIZE;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int FOCAL_OFFSET_X	= (WIDTH/8);
	private static final int FOCAL_OFFSET_Y = (HEIGHT/8);
	private static final int CENTER_OFFSET = (SIZE/2);

	private int posX;
	private int posY;
	private int velX;
	private int velY;

	private Entity focalEntity;
	
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

	public int getFocalX()
	{
		return posX + FOCAL_OFFSET_X;
	}
	
	public int getFocalY()
	{
		return posY - FOCAL_OFFSET_Y;
	}

	public void follow(Entity focalEntityIn)
	{
		focalEntity = focalEntityIn;
		if(focalEntity == null)
			setVelocity(0,0);
	}

	public void think()
	{
		if(focalEntity != null)
		{
			int ex = focalEntity.getX() + (CENTER_OFFSET);
			int ey = focalEntity.getY() - (CENTER_OFFSET);
			int fx = getFocalX();
			int fy = getFocalY();
			int xc = 0;
			int yc = 0;
			if(ex > (fx + RANGE))
				xc++;
			if(ex < (fx - RANGE))
				xc--;
			if(ey > (fy + RANGE))
				yc++;
			if(ey < (fy - RANGE))
				yc--;

			setVelocity(xc, yc);
		}

		posX+=velX;
		posY+=velY;
	}
}
