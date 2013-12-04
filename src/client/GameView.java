package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.Direction;
import server.IGameController;
import server.Tile;

public class GameView extends JFrame implements ActionListener, IGameView
{

	private static final long serialVersionUID = 1L;
	private IGameController controller;
	
	//private JPanel mainPanel = new JPanel();
	private JPanel boardPanel = new JPanel();
	private JPanel tilePanel = new JPanel();
	
	//private JPanel objectivePanel = new JPanel();
	
	private JPanel topArrowPanel = new JPanel();
	private JPanel rightArrowPanel = new JPanel();
	private JPanel bottomArrowPanel = new JPanel();
	private JPanel leftArrowPanel = new JPanel();
	
	private LinkedList<JButton> listArrowButtons = new LinkedList<JButton>();
	
	private JButton btnTopLeftArrow = new JButton();
	private JButton btnTopCenterArrow = new JButton();
	private JButton btnTopRightArrow = new JButton();
	
	private JButton btnLeftUpArrow = new JButton();
	private JButton btnLeftCenterArrow = new JButton();
	private JButton btnLeftDownArrow = new JButton();
	
	private JButton btnRightUpArrow = new JButton();
	private JButton btnRightCenterArrow = new JButton();
	private JButton btnRightDownArrow = new JButton();
	
	private JButton btnDownLeftArrow = new JButton();
	private JButton btnDownCenterArrow = new JButton();
	private JButton btnDownRightArrow = new JButton();
	
	private JLabel[][] board = new JLabel[7][7];
	private JLabel nextTile = new JLabel();
	
	private JButton btnNextTurn = new JButton("Fin de tour");
	
	public GameView(IGameController controller)
	{
		this.controller = controller;
		
		this.setArrowButtons();
		this.setLayout();
		
		this.btnNextTurn.setEnabled(false);
	}
	
	private void setArrowButtons()
	{
		this.listArrowButtons.add(this.btnDownRightArrow);
		this.listArrowButtons.add(this.btnDownLeftArrow);
		this.listArrowButtons.add(this.btnDownCenterArrow);
		
		this.listArrowButtons.add(this.btnTopRightArrow);
		this.listArrowButtons.add(this.btnTopLeftArrow);
		this.listArrowButtons.add(this.btnTopCenterArrow);
		
		this.listArrowButtons.add(this.btnRightUpArrow);
		this.listArrowButtons.add(this.btnRightCenterArrow);
		this.listArrowButtons.add(this.btnRightDownArrow);
		
		this.listArrowButtons.add(this.btnLeftUpArrow);
		this.listArrowButtons.add(this.btnLeftCenterArrow);
		this.listArrowButtons.add(this.btnLeftDownArrow);
	}
	
	private void buildArrowLayout(Direction arrowType)
	{
		switch (arrowType)
		{
		case UP:
			topArrowPanel.setLayout(new GridLayout(0, 9));
			
			topArrowPanel.add(this.nextTile);
			
			for (int k = 1; k < 9; k++)
			{
				URL input = this.getClass().getResource("/img/arrow_down.jpg");
				ImageIcon img = new ImageIcon(input);
				
				if(k % 2 == 0 && k > 0 && k < 8)
				{
					this.addButtonToTopPanel(k, img);
				}
				else
				{
					topArrowPanel.add(new JLabel());
				}
			}
			break;
			
		case RIGHT:
			rightArrowPanel.setLayout(new GridLayout(0, 1));
			
			for (int j = 0; j < 7; j++)
			{
				URL input = this.getClass().getResource("/img/arrow_left.jpg");
				ImageIcon img = new ImageIcon(input);
				
				if(j % 2 != 0 && j > 0 && j < 8)
				{
					this.addButtonToRightPanel(j, img);
				}
				else
				{
					rightArrowPanel.add(new JLabel());
				}
			}
			break;
			
		case DOWN:
			bottomArrowPanel.setLayout(new GridLayout(0, 9));
			
			for (int k = 0; k < 8; k++)
			{
				URL input = this.getClass().getResource("/img/arrow_up.jpg");
				ImageIcon img = new ImageIcon(input);
				
				if(k % 2 == 0 && k > 0 && k < 8)
				{
					this.addButtonToBottomPanel(k, img);
				}
				else
				{
					bottomArrowPanel.add(new JLabel());
				}
			}
			
			this.btnNextTurn.addActionListener(this);
			this.btnNextTurn.setSize(this.btnDownCenterArrow.getWidth(), this.btnDownCenterArrow.getHeight());
			this.btnNextTurn.setBorder(BorderFactory.createEmptyBorder());
			
			bottomArrowPanel.add(this.btnNextTurn);
			break;
			
		case LEFT:
			leftArrowPanel.setLayout(new GridLayout(0, 1));
			
			for (int j = 0; j < 7; j++)
			{
				URL input = this.getClass().getResource("/img/arrow_right.jpg");
				ImageIcon img = new ImageIcon(input);
				
				if(j % 2 != 0 && j > 0 && j < 8)
				{
					this.addButtonToLeftPanel(j, img);
				}
				else
				{
					leftArrowPanel.add(new JLabel());
				}
			}
			break;
		}
			
	}
	
	private void setButton(JButton button, final ImageIcon img)
	{
		button.setIcon(img);
		button.addActionListener(this);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBorder(BorderFactory.createEmptyBorder());
	}
	
	private void addButtonToBottomPanel(final int index, final ImageIcon img)
	{
		if(index == 2)
		{
			this.setButton(this.btnDownLeftArrow, img);
			this.bottomArrowPanel.add(this.btnDownLeftArrow);
		}
		else if(index == 4)
		{
			this.setButton(this.btnDownCenterArrow, img);
			this.bottomArrowPanel.add(this.btnDownCenterArrow);
		}
		else
		{
			this.setButton(this.btnDownRightArrow, img);
			this.bottomArrowPanel.add(this.btnDownRightArrow);
		}
	}
	
	private void addButtonToTopPanel(final int index, final ImageIcon img)
	{
		if(index == 2)
		{
			this.setButton(this.btnTopLeftArrow, img);
			this.topArrowPanel.add(this.btnTopLeftArrow);
		}
		else if(index == 4)
		{
			this.setButton(this.btnTopCenterArrow, img);
			this.topArrowPanel.add(this.btnTopCenterArrow);
		}
		else
		{
			this.setButton(this.btnTopRightArrow, img);
			this.topArrowPanel.add(this.btnTopRightArrow);
		}
	}
	
	private void addButtonToLeftPanel(final int index, final ImageIcon img)
	{
		if(index == 1)
		{
			this.setButton(this.btnLeftUpArrow, img);
			this.leftArrowPanel.add(this.btnLeftUpArrow);
		}
		else if(index == 3)
		{
			this.setButton(this.btnLeftCenterArrow, img);
			this.leftArrowPanel.add(this.btnLeftCenterArrow);
		}
		else
		{
			this.setButton(this.btnLeftDownArrow, img);
			this.leftArrowPanel.add(this.btnLeftDownArrow);
		}
	}
	
	private void addButtonToRightPanel(final int index, final ImageIcon img)
	{
		if(index == 1)
		{
			this.setButton(this.btnRightUpArrow, img);
			this.rightArrowPanel.add(this.btnRightUpArrow);
		}
		else if(index == 3)
		{
			this.setButton(this.btnRightCenterArrow, img);
			this.rightArrowPanel.add(this.btnRightCenterArrow);
		}
		else
		{
			this.setButton(this.btnRightDownArrow, img);
			this.rightArrowPanel.add(this.btnRightDownArrow);
		}
	}
	
	private void setLayout()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
        this.setFocusable(true);
        
        this.buildBoardLayout();
        
        this.add(this.topArrowPanel, BorderLayout.NORTH);
        this.add(this.rightArrowPanel, BorderLayout.EAST);
        this.add(this.bottomArrowPanel, BorderLayout.SOUTH);
        this.add(this.leftArrowPanel, BorderLayout.WEST);
        this.add(this.tilePanel, BorderLayout.CENTER);
        
        //this.buildObjectivesLayout();
        
        //mainPanel.add(boardPanel, BorderLayout.WEST);
        
        //this.add(mainPanel);
        this.pack();
        this.repaint();
	}
	
	private void buildBoardLayout()
	{
		this.buildTileGridLayout();
		

        this.buildArrowLayout(Direction.UP);
        this.buildArrowLayout(Direction.RIGHT);
        this.buildArrowLayout(Direction.DOWN);
        this.buildArrowLayout(Direction.LEFT);
        /*
		boardPanel.setLayout(new GridLayout(0, 3));
		boardPanel.add(new JLabel("TopLeft"));
		boardPanel.add(new JLabel("TopMid"));
		boardPanel.add(new JLabel("TopRight"));
		boardPanel.add(new JLabel("MidLeft"));
		boardPanel.add(tilePanel, BorderLayout.CENTER);

		boardPanel.add(new JLabel("MidRight"));
		boardPanel.add(new JLabel("BottomLeft"));
		boardPanel.add(new JLabel("BottomMid"));
		boardPanel.add(new JLabel("BottomRight"));*/
		
	
		
		/*
		JButton button;
		GridBagConstraints c = new GridBagConstraints();

		button = new JButton("Button 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		boardPanel.add(button, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		boardPanel.add(button, c);

		button = new JButton("Button 3");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		boardPanel.add(button, c);
		
		boardPanel.add(tilePanel);

		button = new JButton("Long-Named Button 4");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		boardPanel.add(button, c);

		button = new JButton("5");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		boardPanel.add(button, c);
		*/
	}
	
	private void buildTileGridLayout()
	{
		this.setTileGridLayoutFromTable(this.controller.getModel().getBoard());
		
		URL input = this.getClass().getResource(this.controller.getModel().getNextTile().getPath());
		ImageIcon img = new ImageIcon(input);
		
		this.nextTile.setIcon(img);
	}
	
	private void setTileGridLayoutFromTable(Tile[][] board)
	{
		this.tilePanel.setLayout(new GridLayout(7, 7));
		
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				URL input = this.getClass().getResource(board[j][i].getPath());
				ImageIcon img = new ImageIcon(input);
				
				JLabel temp = new JLabel(img);
				
				this.board[j][i] = temp;
				
				this.tilePanel.add(temp);
			}
		}
	}
	
	private void buildObjectivesLayout()
	{
		
	}
	
	private void setArrowButtonsEnabled(boolean isEnabled)
	{
		for(JButton button : this.listArrowButtons)
		{
			button.setEnabled(isEnabled);
		}
	}
	
	@Override
	public void updateBoard(Tile[][] newBoard) 
	{
		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				URL input = this.getClass().getResource(newBoard[j][i].getPath());
				ImageIcon img = new ImageIcon(input);
				
				this.board[j][i].setIcon(img);
			}
		}
	}
	
	@Override
	public void updateNextTile(Tile nextTile) 
	{
		URL input = this.getClass().getResource(nextTile.getPath());
		ImageIcon img = new ImageIcon(input);
		
		this.nextTile.setIcon(img);
	}
	
	@Override
	public void showGameView()
	{
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(this.listArrowButtons.contains(ae.getSource()))
		{
			this.btnNextTurn.setEnabled(true);
			this.setArrowButtonsEnabled(false);
			
			if(ae.getSource() == this.btnDownRightArrow)
			{
				this.controller.shiftTiles(5, Direction.UP);
			}
			else if(ae.getSource() == this.btnDownLeftArrow)
			{
				this.controller.shiftTiles(3, Direction.UP);
			}
			else if(ae.getSource() == this.btnDownLeftArrow)
			{
				this.controller.shiftTiles(1, Direction.UP);
			}
			
			else if(ae.getSource() == this.btnTopRightArrow)
			{
				this.controller.shiftTiles(5, Direction.DOWN);
			}
			else if(ae.getSource() == this.btnTopCenterArrow)
			{
				this.controller.shiftTiles(3, Direction.DOWN);
			}
			else if(ae.getSource() == this.btnTopLeftArrow)
			{
				this.controller.shiftTiles(1, Direction.DOWN);
			}
			
			else if(ae.getSource() == this.btnLeftDownArrow)
			{
				this.controller.shiftTiles(5, Direction.RIGHT);
			}
			else if(ae.getSource() == this.btnLeftCenterArrow)
			{
				this.controller.shiftTiles(3, Direction.RIGHT);
			}
			else if(ae.getSource() == this.btnLeftUpArrow)
			{
				this.controller.shiftTiles(1, Direction.RIGHT);
			}
			
			else if(ae.getSource() == this.btnRightDownArrow)
			{
				this.controller.shiftTiles(5, Direction.LEFT);
			}
			else if(ae.getSource() == this.btnRightCenterArrow)
			{
				this.controller.shiftTiles(3, Direction.LEFT);
			}
			else if(ae.getSource() == this.btnRightUpArrow)
			{
				this.controller.shiftTiles(1, Direction.LEFT);
			}
		}
		else
		{
			if(ae.getSource() == this.btnNextTurn)
			{
				this.controller.nextPlayer();
			}
		}
	}
}
