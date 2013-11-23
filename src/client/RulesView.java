package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

public class RulesView extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -5741783231513206199L;
	
	private JButton confirmButton = new JButton("Ok");
	private JCheckBox checkButton = new JCheckBox("J'ai lu et compris les règlements");
	
	public RulesView()
	{
		this.setLayout(new BorderLayout());
		this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Benoît\\Documents\\GitHub\\TP2_MasterLaby\\src\\img\\Reglements.jpg")));
		this.setLayout(new FlowLayout());
		this.add(this.setButtonsPanel(), BorderLayout.SOUTH);
		
		this.configureWindow();
	}
	
	private JPanel setRulesPanel()
	{
		JPanel imagePanel = new JPanel();
		
		this.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Benoît\\Documents\\GitHub\\TP2_MasterLaby\\src\\img\\Reglements.jpg")));
		
		return imagePanel;
	}
	
	private JPanel setButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		
		this.confirmButton.setEnabled(false);
		this.confirmButton.addActionListener(this);
		this.checkButton.addActionListener(this);
		
		buttonsPanel.add(this.checkButton);
		buttonsPanel.add(this.confirmButton);
		
		return buttonsPanel;
	}
	
	private void configureWindow()
	{
		this.setTitle("Règlements");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();;
        this.pack();
        this.setLocation(((screenSize.width-getWidth())/2), ((screenSize.height-getHeight())/2));
        this.setResizable(false);
        this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == this.confirmButton)
		{
			this.dispose();
		}
		else if(ae.getSource() == this.checkButton)
		{
			this.confirmButton.setEnabled(this.checkButton.isSelected());
		}
	}
}
