package screens.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.FontUIResource;

import models.Person;
import shared.Constants;
import shared.Methods;
import shared.screens.AccountPanel;
import shared.components.AppButton;
import shared.screens.LogoLabel;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = -4877032747183645341L;

	//Fields
	private Person person;
	private AppButton butTransfer, 
					butHistory, 
					butReport, butExit,
					butInput;
	private AccountPanel panelAcc;
	private LogoLabel logo;
	private JLabel logoDesc;
	
	public Menu ()
	{
		super();
		this.setLayout(new BorderLayout());
		//this.initMenuAdmin();
		this.initMenuUser();
		
	}
	
	private void initMenuAdmin()
	{
		//Initialization
		
		this.butHistory = new AppButton("History");
		this.butTransfer = new AppButton("Transfer");
		this.butReport = new AppButton("Report");
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel();
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel();
		JPanel mainPanel = new JPanel(new BorderLayout());
		JScrollPane scrollpane = new JScrollPane(mainPanel);
		this.panelAcc = new AccountPanel();
		
		//Properties 
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		this.butTransfer.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butTransfer.setFont(Constants.FONT_GENERAL_BOLD);
		this.butTransfer.setPreferredSize(new Dimension(220,150));
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		this.butReport.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butReport.setFont(Constants.FONT_GENERAL_BOLD);
		this.butReport.setPreferredSize(new Dimension(220,150));
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Add to panelTop 
		panelTop.add(panelAcc);
		
		//Add to panelButton
		panelButton.add(butTransfer);
		panelButton.add(butHistory);
		panelButton.add(butReport);
		
		//Add to panelCenter
		panelCenter.add(logo);
		panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
		panelCenter.add(logoDesc);
		panelCenter.add(Box.createRigidArea(new Dimension(0,0)));
		panelCenter.add(panelButton);
		
		//Add to panelBottom 
		panelBottom.add(butExit);
		
		//Add to MainPanel
		mainPanel.add(panelTop,BorderLayout.NORTH);
		mainPanel.add(panelCenter,BorderLayout.CENTER);
		mainPanel.add(panelBottom,BorderLayout.SOUTH);	
		
		this.add(scrollpane);
		
	}
	
	private void initMenuUser() {
		this.butHistory = new AppButton("History");
		this.butInput = new AppButton("Input");
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel();
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		this.panelAcc = new AccountPanel();
		
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel();
		JPanel mainPanel = new JPanel();
		JScrollPane scrollpane = new JScrollPane(mainPanel);
		
		
		//Properties 
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		this.butInput.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butInput.setFont(Constants.FONT_GENERAL_BOLD);
		this.butInput.setPreferredSize(new Dimension(220,150));
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Add to panelTop 
		panelTop.add(panelAcc);
		
		//Add to panelButton 
		panelButton.add(butInput);
		panelButton.add(butHistory);
		
		//Add to panelCenter
		panelCenter.add(logo);
		panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
		panelCenter.add(logoDesc);
		panelCenter.add(Box.createRigidArea(new Dimension(0,0)));
		panelCenter.add(panelButton);
		
		//Add to panelBottom 
		panelBottom.add(butExit);
		
		//Add to MainPanel
		mainPanel.add(panelTop,BorderLayout.NORTH);
		mainPanel.add(panelCenter,BorderLayout.CENTER);
		mainPanel.add(panelBottom,BorderLayout.SOUTH);
		this.add(scrollpane);
		
		
	}
	
	
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		Menu mn = new Menu();
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(mn);
		
		frame.setVisible(true);
	}
}
