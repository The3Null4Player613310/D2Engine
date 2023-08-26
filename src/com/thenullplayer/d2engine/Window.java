package com.thenullplayer.d2engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Window extends JFrame
{
	private final int WIDTH = 500; 
	private final int HEIGHT = 500;
	private final String TITLE = "D2Engine";
	
	private Client client;

	public Window()
	{
		super();
	}

	public void init(Client clientIn)
	{
		client = clientIn;
		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new CloseAdapter());
		this.setVisible(true);
	}

	public void quit()
	{
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}


	private class CloseAdapter extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent eventIn)
		{
			client.quit();
		}
	}
}
