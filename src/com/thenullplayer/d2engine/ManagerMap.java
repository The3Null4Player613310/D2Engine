package com.thenullplayer.d2engine;

public class ManagerMap implements Manager
{
	private final static int MAP_WIDTH = 255;
	private final static int MAP_HEIGHT = 255;
	static final String DIR_MAP = Client.DIR_MAP;

	private static ManagerMap manager;

	private Tile map[][];

	private ManagerMap()
	{
		map = new Tile[MAP_HEIGHT][MAP_WIDTH];

		for(int i=0; i<map.length; i++)
		{
			for(int j=0; j<map[i].length; j++)
			{
				map[i][j] = new Tile();
				map[i][j].setPos(j,i);
			}
		}
	}

	private void load()
	{
	}

	public Tile getTile(int x, int y)
	{
		if(x<0)
			x+=MAP_WIDTH;
		if(y<0)
			y+=MAP_HEIGHT;
		
		return map[y][x];
	}

	public void think()
	{
	}

	static public ManagerMap getInstance()
	{
		if(manager == null)
			manager = new ManagerMap();
		return manager;
	}
}
