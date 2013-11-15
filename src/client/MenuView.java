package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuView extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 570336988907402917L;
	private MenuController controller;
	
	private JButton btnExit = new JButton("Quitter");
	private JButton btnStart = new JButton("");
	private JButton btnRules = new JButton("Règlements");
	
	public MenuView(MenuController controller)
	{
		this.controller = controller;
		
		this.setLayout();
	}
	
	private void setLayout()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == this.btnExit)
		{
			this.dispose();
		}
	}
}
