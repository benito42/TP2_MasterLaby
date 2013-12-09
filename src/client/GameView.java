package client;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import server.Avatar;
import server.Direction;
import server.Player;
import server.Tile;

public class GameView extends JFrame implements ActionListener, IGameView
{
	private static final long serialVersionUID = 8358872970122547830L;

	private final ClientGameController controller;
	private int noPlayer;
	
	//private JPanel mainPanel = new JPanel();
	private JPanel boardPanel = new JPanel();
	private JPanel tilePanel = new JPanel();
	
	//private JPanel objectivePanel = new JPanel();
	
	private final JPanel topArrowPanel = new JPanel();
	private final JPanel rightArrowPanel = new JPanel();
	private final JPanel bottomArrowPanel = new JPanel();
	private final JPanel leftArrowPanel = new JPanel();
	
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
	
	BufferedImage backGroundImage;
	BufferedImage frontImage;
	BufferedImage scaled;
	JLabel label;
	
	public GameView(ClientGameController controller)
	{
		this.controller = controller;
		this.addWindowListener(new WindowHandler());
		
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
	
	public void setLayout()
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
	
	public void buildPlayers(Tile[][] board)
	{

		JLayeredPane layeredPane = new JLayeredPane();
		//layeredPane.setPreferredSize(new Dimension(150, 150));
		//layeredPane.setBorder(BorderFactory.createTitledBorder("Test Layered Pane"));
		
		URL inputTile = this.getClass().getResource(board[0][0].getPath());
		ImageIcon imgTile = new ImageIcon(inputTile);
		JLabel tempTile = new JLabel(imgTile);
		
		URL inputAvatar = this.getClass().getResource(Avatar.commando.getAvatarPath());
		ImageIcon imgAvatar = new ImageIcon(inputAvatar);
		JLabel tempAvatar = new JLabel(imgAvatar);
		
		layeredPane.add(tempTile, 0);
		layeredPane.add(tempAvatar, 1);
		
		this.tilePanel.add(layeredPane);
	}
	
	private BufferedImage buildPlayers(LinkedList<Player> playerList)
	{
		BufferedImage buffImageTemp = null;
		for (Player p : playerList)
			{
				if (buffImageTemp == null)
				{
					buffImageTemp = imageOnImage(createBufferedImage(this.controller.getBoard()[0][0].getPath()), 
							createBufferedImage(p.getAvatar().getAvatarPath()), 
							PositionOnTile.downLeft.getPositionOnTile(p.getPlayerNumber()));
				}
				else
				{
					buffImageTemp = imageOnImage(buffImageTemp, 
							createBufferedImage(p.getAvatar().getAvatarPath()), 
							PositionOnTile.downLeft.getPositionOnTile(p.getPlayerNumber()));
				}
			}
		
		return buffImageTemp;
	}
	
	private BufferedImage createBufferedImage(String path)
	{
		URL inputTile = this.getClass().getResource(path);
		BufferedImage buffImg = null;
		try
		{
			buffImg = ImageIO.read(inputTile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return buffImg;
	}
	
	private BufferedImage imageOnImage(BufferedImage backGroundImage, BufferedImage frontImage, PositionOnTile positionOnTile)
	{
		this.backGroundImage = backGroundImage;
		this.frontImage = frontImage;
		
	    scaled = new BufferedImage(frontImage.getWidth()/2,frontImage.getHeight()/2,BufferedImage.TYPE_INT_RGB);
	    Graphics g = scaled.getGraphics();
	    g.drawImage(frontImage,0,0,scaled.getWidth(),scaled.getHeight(),null);
	    g.dispose();
	
	    Graphics g2 = backGroundImage.getGraphics();
		int x = positionOnTile.getX();
		int y = positionOnTile.getY();
	
		g2.drawImage( scaled, x, y, null );
		g2.dispose();
	
	    return backGroundImage;
	}
	
	private void buildBoardLayout()
	{
		this.setTileGridLayoutFromTable();

        this.buildArrowLayout(Direction.UP);
        this.buildArrowLayout(Direction.RIGHT);
        this.buildArrowLayout(Direction.DOWN);
        this.buildArrowLayout(Direction.LEFT);
	}
	
	private void setTileGridLayoutFromTable()
	{
		this.tilePanel.setLayout(new GridLayout(7, 7));
		
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				JLabel jLabelTemp = new JLabel(new ImageIcon());
				
				this.board[j][i] = jLabelTemp;
					
				this.tilePanel.add(jLabelTemp);
			}
		}
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
				ImageIcon imageIconTemp;
				BufferedImage buffImgTemp;
				
				//create tile with objectives
				if (newBoard[j][i].getObjective() != null && newBoard[j][i].getObjective().isObjectiveReached() == false)
				{
					buffImgTemp = imageOnImage(createBufferedImage(newBoard[0][0].getPath()), 
							createBufferedImage(newBoard[0][0].getObjective().getPath()), PositionOnTile.mid);
				}
				else//create tile without objectives
				{
					buffImgTemp = createBufferedImage(newBoard[j][i].getPath());
				}
				//add player to tile
				if (!newBoard[j][i].getPlayerList().isEmpty())
				{
					buffImgTemp = buildPlayers(newBoard[j][i].getPlayerList());
				}
				
				imageIconTemp = new ImageIcon(buffImgTemp);
				
				this.board[j][i].setIcon(imageIconTemp);
			}
		}
	}
	
	@Override
	public void updateNextTile(Tile nextTilePath) 
	{
		URL input = this.getClass().getResource(nextTilePath.getPath());
		ImageIcon img = new ImageIcon(input);
		
		this.nextTile.setIcon(img);
	}
	
	@Override
	public void updateNoPlayer(int noPlayer) 
	{
		this.noPlayer = noPlayer;
	}
	
	@Override
	public void updateTurnChange(int noActivePlayer) 
	{
		if(this.noPlayer == noActivePlayer)
		{
			this.setArrowButtonsEnabled(true);
		}
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
				this.btnNextTurn.setEnabled(false);
				this.controller.nextPlayer();
			}
		}
	}
	
	private class WindowHandler implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent e)
		{
		}

		@Override
		public void windowClosed(WindowEvent e)
		{
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			controller.closeRequest();
			dispose();
		}

		@Override
		public void windowDeactivated(WindowEvent e)
		{
		}

		@Override
		public void windowDeiconified(WindowEvent e)
		{
		}

		@Override
		public void windowIconified(WindowEvent e)
		{
		}

		@Override
		public void windowOpened(WindowEvent e)
		{
		}

	}
}
