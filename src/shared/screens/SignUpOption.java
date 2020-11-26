package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import giantsweetroll.gui.swing.TextAreaManager;
import shared.Constants;

public class SignUpOption extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3939126702743868715L;
	
	//Fields
	private JLabel labTitle;
	private JTextArea taDetails;
	private JButton button;
	
	//Constants
	private final int GENERAL_PADDING = 5;
	
	//Constructor
	public SignUpOption(String title, String details, String buttonText, ActionListener onPressed)
	{
		//Initialization
		this.labTitle = new JLabel(title);
		this.taDetails = new JTextArea(details);
		this.button = new JButton(buttonText);
//		SpringLayout spr = new SpringLayout();
		
		//Properties
//		this.setLayout(spr);
		this.setLayout(new BorderLayout(10, 10));
		this.setOpaque(false);
		this.labTitle.setFont(Constants.FONT_SUB_TITLE);
		this.labTitle.setHorizontalAlignment(SwingConstants.CENTER);
		TextAreaManager.autoConfigureTextArea(this.taDetails, false);
		this.taDetails.setBorder(null);
		this.button.setBackground(Constants.COLOR_BUTTON_BASE);
		this.button.setForeground(Color.WHITE);
		//TODO: Add ActionListener to button
		/*
		spr.putConstraint(SpringLayout.NORTH, this.labTitle, GENERAL_PADDING, SpringLayout.NORTH, this);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.labTitle, 0, SpringLayout.HORIZONTAL_CENTER, this);
		spr.putConstraint(SpringLayout.SOUTH, this.button, this.GENERAL_PADDING * -1, SpringLayout.SOUTH, this);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.button, this.GENERAL_PADDING, SpringLayout.HORIZONTAL_CENTER, this);
		spr.putConstraint(SpringLayout.NORTH, this.taDetails, this.GENERAL_PADDING, SpringLayout.SOUTH, this.labTitle);
		spr.putConstraint(SpringLayout.WEST, this.taDetails, this.GENERAL_PADDING, SpringLayout.WEST, this);
		spr.putConstraint(SpringLayout.EAST, this.taDetails, this.GENERAL_PADDING * -1, SpringLayout.EAST, this);
		spr.putConstraint(SpringLayout.SOUTH, this.taDetails, this.GENERAL_PADDING * -1, SpringLayout.NORTH, this.button);
		*/
		
		//Add to panel
		this.add(this.labTitle, BorderLayout.NORTH);
		this.add(this.taDetails, BorderLayout.CENTER);
		this.add(this.button, BorderLayout.SOUTH);
	}
	
	//Testing
	public static void main(String args[])
	{
		//Initialization
		JFrame frame = new JFrame();
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(new SignUpOption("Title", "Content", "Button", null));
		
		frame.setVisible(true);
	}
}
