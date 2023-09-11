package com.thenullplayer.d2engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditorSprite extends JFrame
{
	private final int WIDTH = 800; 
	private final int HEIGHT = 600;
	private final String TITLE = "Sprite Editor";
	private final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

	private final String DIR_SPRITE = Client.DIR_SPRITE; 

	final static double BAR_SCALE = (9d/32d);
	final static int SLIDER_MINOR = 1;
	final static int SLIDER_MIN = 0;
	final static int SLIDER_MAX = 255;
	final static int SLIDER_INC = 1;
	
	final static int SPRITE_WIDTH = 16;
	final static int SPRITE_HEIGHT =16;
	final static int SPRITE_SCALE = 24;

	final static int MASK_PALLET = 0x03;

	final static int[] HEADER_IDENT = {0x53, 0x50, 0x52};

	FlowLayout layout;

	public Color[] pallet = {new Color(128, 128, 128, 255), Color.red, Color.green, Color.blue};
	public byte[][] sprite = new byte[SPRITE_HEIGHT][SPRITE_WIDTH];

	JButton loadButton;
	JButton saveButton;
	JTextField fileNameField;
	JSlider primaryColorSliderR;
	JSlider primaryColorSliderG;
	JSlider primaryColorSliderB;
	JSlider secondaryColorSliderR;
	JSlider secondaryColorSliderG;
	JSlider secondaryColorSliderB;
	JSlider ternaryColorSliderR;
	JSlider ternaryColorSliderG;
	JSlider ternaryColorSliderB;

	public EditorSprite()
	{
		super();

		layout = new FlowLayout();

		this.setTitle(TITLE);
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(SIZE);
		this.setResizable(false);
		this.setLayout(layout);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new CloseAdapter());
		
		ViewerSprite spriteViewer = new ViewerSprite(this);
		this.add(spriteViewer);

		InputGrid input = new InputGrid(this);
		this.add(input);

		loadButton = new JButton();
		loadButton.setText("LOAD");
		loadButton.addActionListener(new LoadListener(this));
		this.add(loadButton);

		saveButton = new JButton();
		saveButton.setText("SAVE");
		saveButton.addActionListener(new SaveListener(this));
		this.add(saveButton);

		fileNameField = new JTextField();
		fileNameField.setPreferredSize(new Dimension((WIDTH * 3) / 4, 28));
		this.add(fileNameField);

		BarListener barListener = new BarListener(this);

		JLabel primaryLabelRed = new JLabel();
		primaryLabelRed.setText("   PRIMARY");
		this.add(primaryLabelRed);

		primaryColorSliderR = new JSlider();
		primaryColorSliderR.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		primaryColorSliderR.setMinimum(SLIDER_MIN);
		primaryColorSliderR.setMaximum(SLIDER_MAX);
		primaryColorSliderR.setMinorTickSpacing(SLIDER_MINOR);
		primaryColorSliderR.addChangeListener(barListener);
		this.add(primaryColorSliderR);

		primaryColorSliderG = new JSlider();
		primaryColorSliderG.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		primaryColorSliderG.setMinimum(SLIDER_MIN);
		primaryColorSliderG.setMaximum(SLIDER_MAX);
		primaryColorSliderG.setMinorTickSpacing(SLIDER_MINOR);
		primaryColorSliderG.addChangeListener(barListener);
		this.add(primaryColorSliderG);

		primaryColorSliderB = new JSlider();
		primaryColorSliderB.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		primaryColorSliderB.setMinimum(SLIDER_MIN);
		primaryColorSliderB.setMaximum(SLIDER_MAX);
		primaryColorSliderB.setMinorTickSpacing(SLIDER_MINOR);
		primaryColorSliderB.addChangeListener(barListener);
		this.add(primaryColorSliderB);
		
		JLabel secondaryLabelRed = new JLabel();
		secondaryLabelRed.setText("SECONDARY");
		this.add(secondaryLabelRed);

		secondaryColorSliderR = new JSlider();
		secondaryColorSliderR.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		secondaryColorSliderR.setMinimum(SLIDER_MIN);
		secondaryColorSliderR.setMaximum(SLIDER_MAX);
		secondaryColorSliderR.setMinorTickSpacing(SLIDER_MINOR);
		secondaryColorSliderR.addChangeListener(barListener);
		this.add(secondaryColorSliderR);

		secondaryColorSliderG = new JSlider();
		secondaryColorSliderG.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		secondaryColorSliderG.setMinimum(SLIDER_MIN);
		secondaryColorSliderG.setMaximum(SLIDER_MAX);
		secondaryColorSliderG.setMinorTickSpacing(SLIDER_MINOR);
		secondaryColorSliderG.addChangeListener(barListener);
		this.add(secondaryColorSliderG);

		secondaryColorSliderB = new JSlider();
		secondaryColorSliderB.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		secondaryColorSliderB.setMinimum(SLIDER_MIN);
		secondaryColorSliderB.setMaximum(SLIDER_MAX);
		secondaryColorSliderB.setMinorTickSpacing(SLIDER_MINOR);
		secondaryColorSliderB.addChangeListener(barListener);
		this.add(secondaryColorSliderB);
		
		JLabel ternaryLabelRed = new JLabel();
		ternaryLabelRed.setText("    TERNARY");
		this.add(ternaryLabelRed);

		ternaryColorSliderR = new JSlider();
		ternaryColorSliderR.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		ternaryColorSliderR.setMinimum(SLIDER_MIN);
		ternaryColorSliderR.setMaximum(SLIDER_MAX);
		ternaryColorSliderR.setMinorTickSpacing(SLIDER_MINOR);
		ternaryColorSliderR.addChangeListener(barListener);
		this.add(ternaryColorSliderR);

		ternaryColorSliderG = new JSlider();
		ternaryColorSliderG.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		ternaryColorSliderG.setMinimum(SLIDER_MIN);
		ternaryColorSliderG.setMaximum(SLIDER_MAX);
		ternaryColorSliderG.setMinorTickSpacing(SLIDER_MINOR);
		ternaryColorSliderG.addChangeListener(barListener);
		this.add(ternaryColorSliderG);

		ternaryColorSliderB = new JSlider();
		ternaryColorSliderB.setPreferredSize(new Dimension((int) (WIDTH * BAR_SCALE), 28));
		ternaryColorSliderB.setMinimum(SLIDER_MIN);
		ternaryColorSliderB.setMaximum(SLIDER_MAX);
		ternaryColorSliderB.setMinorTickSpacing(SLIDER_MINOR);
		ternaryColorSliderB.addChangeListener(barListener);
		this.add(ternaryColorSliderB);

		this.pack();
		this.setVisible(true);
	}

	public void load() throws Exception
	{
		String fileName = fileNameField.getText();
		
		File file = new File(DIR_SPRITE + fileName);
		
		if(file.exists() && file.isFile())
		{
			
			try
			{
				FileInputStream stream = new FileInputStream(file);
				try
				{
					int headerSize = stream.read();
					int[] header = new int[headerSize];
					if(headerSize >= 0x10)
						header[0] = headerSize;
					else
						throw new Exception();
					
					for(int i=1; i<header[0]; i++)
					{
						header[i] = stream.read();
	
						if((i == 1) && (header[i] != HEADER_IDENT[0]))
							throw new Exception();
						if((i == 2) && (header[i] != HEADER_IDENT[1]))
							throw new Exception();
						if((i == 3) && (header[i] != HEADER_IDENT[2]))
							throw new Exception();
						if((i == 4) && (header[i] != 0x10))
							throw new Exception();
						if((i == 5) && (header[i] != 0x10))
							throw new Exception();
						if((i == (header[0]-1)) && (header[i] != 0xFF))
							throw new Exception();
					}

					primaryColorSliderR.setValue(header[6]);
					primaryColorSliderG.setValue(header[7]);
					primaryColorSliderB.setValue(header[8]);
					secondaryColorSliderR.setValue(header[9]);
					secondaryColorSliderG.setValue(header[10]);
					secondaryColorSliderB.setValue(header[11]);
					ternaryColorSliderR.setValue(header[12]);
					ternaryColorSliderG.setValue(header[13]);
					ternaryColorSliderB.setValue(header[14]);
	
					byte[][] data = new byte[header[4]][header[5]];
					for(int i=0; i < header[4]; i++)
					{
						for(int j=0; j < header[5]; j++)
						{
							data[i][j] = (byte) (stream.read() & 0xFF);
						}
					}

					for(int i=0; i<data.length; i++)
					{
						for(int j=0; j<data[i].length; j++)
						{
							sprite[i][j] = data[i][j];
						}
					}
			
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			
				try
				{
					stream.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		updateColors();
	}

	public void save()
	{
		String fileName = fileNameField.getText();

		File file = new File(DIR_SPRITE + fileName);
		
		if(file.exists() && file.isFile())
			file.delete();
		
		try
		{

			file.createNewFile();
	
			FileOutputStream stream = new FileOutputStream(file);
		
			try
			{
				stream.write(0x10);
		
				stream.write(HEADER_IDENT[0]);
				stream.write(HEADER_IDENT[1]);
				stream.write(HEADER_IDENT[2]);
		
				stream.write(SPRITE_WIDTH);
				stream.write(SPRITE_HEIGHT);
			
				for(int i=1; i<pallet.length; i++)
				{
					stream.write(pallet[i].getRed());
					stream.write(pallet[i].getGreen());
					stream.write(pallet[i].getBlue());
				}
	
				stream.write(0xFF);

				for(int i=0; i<SPRITE_HEIGHT; i++)
				{
					for(int j=0; j<SPRITE_WIDTH; j++)
					{
						stream.write(sprite[i][j]);
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				stream.flush();
				stream.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateColors()
	{
		pallet[1] = new Color(primaryColorSliderR.getValue(), primaryColorSliderG.getValue(), primaryColorSliderB.getValue());
		pallet[2] = new Color(secondaryColorSliderR.getValue(), secondaryColorSliderG.getValue(), secondaryColorSliderB.getValue());
		pallet[3] = new Color(ternaryColorSliderR.getValue(), ternaryColorSliderG.getValue(), ternaryColorSliderB.getValue());
		repaint();
	}

	private class CloseAdapter extends WindowAdapter
	{

		@Override
		public void windowClosing(WindowEvent eventIn)
		{
			
		}
	}

	private class LoadListener implements ActionListener
	{
		private final EditorSprite editor;
		
		public LoadListener(EditorSprite editorIn)
		{
			editor = editorIn;
		}
		
		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{

			try
			{
				editor.load();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private class SaveListener implements ActionListener
	{
		private final EditorSprite editor;
		
		public SaveListener(EditorSprite editorIn)
		{
			editor = editorIn;
		}
		
		@Override
		public void actionPerformed(ActionEvent actionEventIn)
		{
			editor.save();
		}
	}

	private class BarListener implements ChangeListener
	{

		EditorSprite editor;

		BarListener(EditorSprite editorIn)
		{
			editor = editorIn;
		}

		@Override
		public void stateChanged(ChangeEvent e)
		{
			editor.updateColors();
		}
	}
	
	private class ViewerSprite extends JPanel
	{
		final static int WIDTH = SPRITE_HEIGHT * SPRITE_SCALE;
		final static int HEIGHT = SPRITE_HEIGHT * SPRITE_SCALE;
		public final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

		EditorSprite editor;

		public ViewerSprite(EditorSprite editorIn)
		{
			editor = editorIn;

			this.setSize(WIDTH, HEIGHT);
			this.setPreferredSize(SIZE);
		}

		@Override
		public void paint(Graphics gIn)
		{
			for(int i=0; i<SPRITE_HEIGHT; i++)
			{
				for(int j=0; j<SPRITE_WIDTH; j++)
				{
					gIn.setColor(editor.pallet[sprite[i][j]]);
					gIn.fillRect(j*SPRITE_SCALE, i*SPRITE_SCALE, SPRITE_SCALE, SPRITE_SCALE);
				}
			}
		}
	}

	private class InputGrid extends JPanel
	{
		//final static int SCALE = 16;
		final static int WIDTH = SPRITE_HEIGHT * SPRITE_SCALE;
		final static int HEIGHT = SPRITE_HEIGHT * SPRITE_SCALE;
		public final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
		public final Dimension SIZE_BUTTON = new Dimension(SPRITE_SCALE-5, SPRITE_SCALE-5);

		private EditorSprite editor;

		private JButton[][] buttonGrid = new JButton[SPRITE_WIDTH][SPRITE_HEIGHT];
		
		public InputGrid(EditorSprite editorIn)
		{
			editor = editorIn;

			this.setSize(WIDTH, HEIGHT);
			this.setPreferredSize(SIZE);

			for(int i=0; i<buttonGrid.length; i++)
			{
				for(int j=0; j<buttonGrid[i].length; j++)
				{
					buttonGrid[i][j] = new JButton();
					buttonGrid[i][j].setPreferredSize(SIZE_BUTTON);
					buttonGrid[i][j].setBackground(editor.pallet[editor.sprite[i][j]]);
					buttonGrid[i][j].addActionListener(new ButtonListener(editor, buttonGrid[i][j], j, i));
					this.add(buttonGrid[i][j]);
				}
			}
		}

		@Override
		public void paint(Graphics gIn)
		{
			super.paint(gIn);

			for(int i=0; i<buttonGrid.length; i++)
			{
				for(int j=0; j<buttonGrid[i].length; j++)
				{
					buttonGrid[i][j].setBackground(editor.pallet[editor.sprite[i][j]]);
				}
			}			
		}

		private class ButtonListener implements ActionListener
		{

			private final EditorSprite editor;
			private final JButton button;
			private final int x;
			private final int y;

			public ButtonListener(EditorSprite editorIn, JButton buttonIn, int xIn, int yIn)
			{
				editor = editorIn;
				button = buttonIn;
				x = xIn;
				y = yIn;
			}

			@Override
			public void actionPerformed(ActionEvent actionEventIn)
			{
				editor.sprite[y][x]++;
				editor.sprite[y][x] = (byte) (editor.sprite[y][x] & MASK_PALLET);
				button.setBackground(editor.pallet[editor.sprite[y][x]]);
				editor.repaint();
			}
		}
	}

	public static void main(String[] args)
	{
		new EditorSprite();
	}
}
