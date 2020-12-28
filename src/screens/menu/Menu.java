package screens.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.ImageManager;
import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Person;
import screens.history.TransactionHistory;
import screens.history.TransferHistory;
import screens.input.InputTransactions;
import screens.input.TransferFunds;
import screens.report.ReportPage;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.AppButton;
import shared.components.LogoLabel;
import shared.screens.AccountPanel;


public class Menu extends JPanel{
	
	private static final long serialVersionUID = -4877032747183645341L;

	//Fields
	private AppButton butTransfer, 
					butHistory, 
					butReport, butExit,
					butInput, butCategories;
	private AccountPanel panelAcc;
	private LogoLabel logo;
	private JLabel logoDesc, 
					addCatIconLabel, addCatLabel;
	private boolean asAdmin;
	
	
	public Menu (Person person, boolean asAdmin)
	{
		super();
		this.asAdmin = asAdmin;
		this.setLayout(new BorderLayout());
		
		if (!this.asAdmin) {
		this.initMenuUser(person);
		}
		else {
			this.initMenuAdmin(person);	
		}
		
		//Properties 	
	}
	
	
	private void initMenuAdmin(Person person)
	{
		//Initialization
		this.panelAcc = new AccountPanel();
		this.butHistory = new AppButton("History", Constants.ICON_HISTORY);
		this.butTransfer = new AppButton("Transfer", Constants.ICON_TRANSFER);
		this.butReport = new AppButton("Report", Constants.ICON_REPORT);
		this.butCategories = new AppButton();
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel(LogoLabel.BIG);
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		this.addCatIconLabel = new JLabel(Constants.ICON_CATEGORIES);
		this.addCatLabel = new JLabel ("   Categories");
		SpringLayout spr = new SpringLayout();
		JPanel panelCategoriesBtn = new JPanel();
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel();
		JPanel mainPanel = new JPanel(new BorderLayout());
		JScrollPane scrollpane = ScrollPaneManager.generateDefaultScrollPane(mainPanel, 10, 10);
		
		//Properties 
		panelCategoriesBtn.setLayout(new BoxLayout(panelCategoriesBtn,BoxLayout.X_AXIS));
		panelCategoriesBtn.setOpaque(false);
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.butTransfer.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butTransfer.setFont(Constants.FONT_GENERAL_BOLD);
		this.butTransfer.setPreferredSize(new Dimension(220,150));
		this.butTransfer.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.butTransfer.setHorizontalTextPosition(SwingConstants.CENTER);
		this.butTransfer.setForeground(Color.BLACK);
		this.butTransfer.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Main.changeScreen(new TransferFunds(person));
				
			}
		});
		
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		this.butHistory.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.butHistory.setHorizontalTextPosition(SwingConstants.CENTER);
		this.butHistory.setForeground(Color.BLACK);
		this.butHistory.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Main.changeScreen(new TransferHistory(person));
			}
		});
		
		this.butReport.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butReport.setFont(Constants.FONT_GENERAL_BOLD);
		this.butReport.setPreferredSize(new Dimension(220,150));
		this.butReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.butReport.setHorizontalTextPosition(SwingConstants.CENTER);
		this.butReport.setForeground(Color.BLACK);
		this.butReport.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Main.changeScreen(new ReportPage(person));
			}
		});
		
		this.butCategories.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butCategories.setFont(Constants.FONT_GENERAL_BOLD);
		this.butCategories.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.butCategories.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//Main.pushScreen(new CategoryEditor(admin));
			}
		});

		
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butExit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		

	
		//Add to panelTop 
		panelAcc.setAccount(person);
		panelTop.add(panelAcc);
		
		//Add to Categories button 
		panelCategoriesBtn.add(addCatIconLabel);
		panelCategoriesBtn.add(addCatLabel);
		butCategories.add(panelCategoriesBtn);
		
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
		panelCenter.add(butCategories);
		
		//Add to panelBottom 
		panelBottom.add(Box.createRigidArea(new Dimension(0,20)));
		panelBottom.add(butExit);
		
		//Add to MainPanel
		mainPanel.add(panelTop,BorderLayout.NORTH);
		mainPanel.add(panelCenter,BorderLayout.CENTER);
		mainPanel.add(panelBottom,BorderLayout.SOUTH);	
		
		this.add(scrollpane);
		
	}
	
	private void initMenuUser(Person person) {
		this.butHistory = new AppButton("History", Constants.ICON_HISTORY);
		this.butInput = new AppButton("Input", Constants.ICON_INPUT);
		this.butCategories = new AppButton();
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel(LogoLabel.BIG);
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		this.panelAcc = new AccountPanel();
		
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel(); 
		JPanel mainPanel = new JPanel();
		JPanel panelCategories = new JPanel();
		JScrollPane scrollpane = ScrollPaneManager.generateDefaultScrollPane(mainPanel, 10 ,10);
		
		
		//Properties 
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCategories.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.butInput.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butInput.setFont(Constants.FONT_GENERAL_BOLD);
		this.butInput.setPreferredSize(new Dimension(220,150));
		this.butInput.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.butInput.setHorizontalTextPosition(SwingConstants.CENTER);
		this.butInput.setForeground(Color.BLACK);
		this.butInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.changeScreen(new InputTransactions(person));
			}
		});
		
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		this.butHistory.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.butHistory.setHorizontalTextPosition(SwingConstants.CENTER);
		this.butHistory.setForeground(Color.BLACK);
		this.butHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Main.changeScreen(new TransactionHistory(person));
			}
		});
		
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butExit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		
		//Add to panelTop
		panelAcc.setAccount(person);
		panelTop.add(panelAcc);
		
		//Add to panelButton 
		panelButton.add(butInput);
		panelButton.add(butHistory);
		
		//Add to panelCategories
		panelCategories.add(butCategories);
		
		//Add to panelCenter
		panelCenter.add(logo);
		panelCenter.add(Box.createRigidArea(new Dimension(0,0)));
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
	
	@Deprecated
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(ImageManager.scaleImage((new ImageIcon(imgURL, description)).getImage(), 64, 64));
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		Person Person1 = new Person("Adam", "Smith");
		Menu mn = new Menu(Person1,true);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(mn);
		
		frame.setVisible(true);
	}
}
