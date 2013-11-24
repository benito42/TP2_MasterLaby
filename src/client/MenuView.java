package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuView extends JFrame implements ActionListener, IMenuView
{
	private static final long serialVersionUID = 570336988907402917L;
	private IMenuController controller;
	
	private JButton btnExit = new JButton("Quitter");
	private JButton btnStart = new JButton("Démarrer une partie");
	private JButton btnRules = new JButton("Règlements");
	//private JButton btnLeaders = new JButton("Tableau des meneurs");
	
	public MenuView(IMenuController controller)
	{
		this.controller = controller;
		
		this.setLayout();
	}
	
	private void setLayout()
	{
		this.setLayout(new BorderLayout());
		
		URL input = this.getClass().getResource("/img/aMAZEing_LABYRINTH.jpg");
		ImageIcon img = new ImageIcon(input);
		
	    this.setContentPane(new JLabel(img));
	    this.setLayout(new FlowLayout());
	    
	    this.add(this.setButtonsPanel());
		
		this.configureWindow();
	}
	
	private JPanel setButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel.setOpaque(false);
		
		this.btnExit.addActionListener(this);
		this.btnStart.addActionListener(this);
		this.btnRules.addActionListener(this);
		//this.btnLeaders.addActionListener(this);
		
		buttonsPanel.add(this.btnStart);
		buttonsPanel.add(this.btnRules);
		//buttonsPanel.add(this.btnLeaders);
		buttonsPanel.add(this.btnExit);
		
		return buttonsPanel;
	}
	
	private void configureWindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();;
        this.pack();
        this.setLocation(((screenSize.width-getWidth())/2), ((screenSize.height-getHeight())/2));
        this.setResizable(false);
        this.setVisible(true);
	}

	@Override
	public void showView() 
	{
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == this.btnExit)
		{
			this.dispose();
		}
		else if(ae.getSource() == this.btnRules)
		{
			this.controller.showRules();
		}
		/*else if(ae.getSource() == this.btnLeaders)
		{
			this.controller.showLeaders();
		}*/
	}
}
