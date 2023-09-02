package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Menu extends JPanel
{
	//private static int count = 0;

	private boolean isVisible = true;
	public final Window window;
	private Menu branch;
	
	private SpringLayout layout;

	public Menu(Window windowIn)
	{
		super();

		window = windowIn;		

		this.hideMenu();
		this.setPreferredSize(window.SIZE);
		//this.setMinimumSize(window.SIZE);

		layout = new SpringLayout();

		this.setLayout(layout);

		window.add(this, 0);

		this.showMenu();

		//count++;
		//System.out.println(count);
	}

	public Menu(Window windowIn, Menu branchIn)
	{
		this(windowIn);
	
		branch = branchIn;

		branch.hideMenu();
	}

	public void hideMenu()
	{
		if(isVisible)
		{
			isVisible = false;
			this.setVisible(isVisible);
		}
	}

	public void showMenu()
	{
		if(!isVisible)
		{
			isVisible = true;
			this.setVisible(isVisible);
			this.repaint();
			this.requestFocusInWindow();
		}
	}

	public void toggleMenu()
	{
		if(isVisible)
		{
			hideMenu();
		}
		else
		{
			showMenu();
		}
	}

	public void closeMenu()
	{
		if(branch != null)
			branch.showMenu();
		window.remove(this);
		//count--;
		//System.out.println(count);
	}

	public void closeMenuTree()
	{
		if(branch != null)
			branch.closeMenuTree();
		window.remove(this);
		//count--;
		//System.out.println(count);
	}

	Component lastComponent;
	@Override
	public Component add(Component componentIn)
	{
		super.add(componentIn);

		if(lastComponent == null)
		{
			layout.putConstraint(SpringLayout.NORTH, componentIn, 100, SpringLayout.NORTH, this);
		}
		else
		{
			layout.putConstraint(SpringLayout.NORTH, componentIn, 10, SpringLayout.SOUTH, lastComponent);
		}


		layout.putConstraint(SpringLayout.EAST, componentIn, -100, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, componentIn, 100, SpringLayout.WEST, this);

		//layout.putConstraint(SpringLayout.SOUTH, componentIn, -100, SpringLayout.SOUTH, this);

		lastComponent = componentIn;
		return componentIn;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}
