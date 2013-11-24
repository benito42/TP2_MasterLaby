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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.IGameController;

public class GameView extends JFrame implements ActionListener, IGameView
{

	private static final long serialVersionUID = 1L;
	private IGameController controller;
	
	private JPanel mainPanel = new JPanel();
	private JPanel boardPanel = new JPanel(new GridLayout());
	private JPanel tilePanel = new JPanel();
	
	
	//private JPanel objectivePanel = new JPanel();
	
	private JPanel topArrowPanel = new JPanel();
	private JPanel rightArrowPanel = new JPanel();
	private JPanel bottomArrowPanel = new JPanel();
	private JPanel leftArrowPanel = new JPanel();

	
	public GameView(IGameController controller)
	{
		this.controller = controller;
		this.setLayout();
		
	}
	
	private void setLayout()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
        this.setFocusable(true);
        
        this.buildBoardBagLayout();
        this.buildObjectivesLayout();
        
        mainPanel.add(boardPanel, BorderLayout.WEST);
        
        this.add(mainPanel);
        
        this.pack();
	}
	
	private void buildBoardBagLayout()
	{
		this.buildTileGridLayout();
		
		boardPanel.add(tilePanel);
		/*
		boardPanel.setLayout(new GridLayout(3, 1));
		
		//boardPanel.add(new JLabel("TopLeft"));
		boardPanel.add(new JLabel("TopMid"), BorderLayout.NORTH);
		//boardPanel.add(new JLabel("TopRight"));
		//boardPanel.add(new JLabel("MidLeft"), BorderLayout.WEST);
		
		boardPanel.add(tilePanel, BorderLayout.CENTER);

		//boardPanel.add(new JLabel("MidRight"), BorderLayout.EAST);
		//boardPanel.add(new JLabel("BottomLeft"));
		boardPanel.add(new JLabel("BottomMid"), BorderLayout.SOUTH);
		//boardPanel.add(new JLabel("BottomRight"));
		*/
	
		
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
		boardPanel.add(button, c);*/
		
	}
	
	private void buildTileGridLayout()
	{
		tilePanel.setLayout(new GridLayout(7, 7));
		
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				URL input = this.getClass().getResource(controller.getModel().getTile(j, i).getPath());
				ImageIcon img = new ImageIcon(input);
				
				tilePanel.add(new JLabel(img));
			}
		}
	}
	
	private void buildObjectivesLayout()
	{
		
	}
	
	@Override
	public void showGameView()
	{
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
