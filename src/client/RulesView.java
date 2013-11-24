package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RulesView extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -5741783231513206199L;
	
	private JButton confirmButton = new JButton("Ok");
	private JCheckBox checkButton = new JCheckBox("J'ai lu et compris les règlements");
	
	public RulesView()
	{
		this.setLayout(new BorderLayout());
		
		this.add(this.getRulesPanel(), BorderLayout.NORTH);
		this.add(this.getButtonsPanel(), BorderLayout.SOUTH);
		
		this.configureWindow();
	}
	
	private JPanel getRulesPanel()
	{
		JPanel imagePanel = new JPanel();
		
		URL input = this.getClass().getResource("/img/rules.jpg");
		ImageIcon img = new ImageIcon(input);
		
		imagePanel.add(new JLabel(img));
		
		return imagePanel;
	}
	
	private JPanel getButtonsPanel()
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
