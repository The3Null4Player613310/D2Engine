package com.thenullplayer.d2engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter
{
	final static int KEY_W = KeyEvent.VK_W;
	final static int KEY_S = KeyEvent.VK_S;
	final static int KEY_A = KeyEvent.VK_A;
	final static int KEY_D = KeyEvent.VK_D;

	final static int KEY_Q = KeyEvent.VK_Q;
	final static int KEY_E = KeyEvent.VK_E;

	KeyboardListener listener;

	public Keyboard()
	{
	}

	public void setListener(KeyboardListener listenerIn)
	{
		listener = listenerIn;
	}

	@Override
	public void keyPressed(KeyEvent eIn)
	{
		if(listener != null)
		{
			listener.keyDown(eIn.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent eIn)
	{
		if(listener != null)
		{
			listener.keyUp(eIn.getKeyCode());
		}
	}

	public interface KeyboardListener
	{

		public void keyDown(int keyIn);
		public void keyUp(int keyIn);
	}
}
