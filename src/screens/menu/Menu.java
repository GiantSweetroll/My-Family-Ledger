package screens.menu;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.ImageManager;
import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Person;
import screens.history.TransactionHistory;
import screens.history.TransferHistory;
import screens.input.InputTransactions;
import screens.input.TransferFunds;
import screens.report.ReportPage;
import shared.Constants;
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
	private JLabel logoDesc;
	private boolean asAdmin;
	private JPanel panelTop, panelCenter, panelBelow;
	private JScrollPane scroll;
	
	//Constructor
	public Menu (Person person, boolean asAdmin)
	{
		//Initialization
		super();
		this.asAdmin = asAdmin;
		this.initPanelTop(person);
		this.initPanelBelow();
		this.initPanelCenter(person, asAdmin);
		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.panelCenter, 10, 10);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		//Add to panel
		this.add(this.panelTop, BorderLayout.NORTH);
		this.add(this.scroll, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	
	//Private methods
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butExit = new AppButton("Exit");
		
		//Properties
		this.panelBelow.setOpaque(false);
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butExit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		
		//Add to panel
		this.panelBelow.add(this.butExit);
	}
	private void initPanelTop(Person person)
	{
		//Initialization
		this.panelTop = new JPanel(new BorderLayout());
		this.panelAcc = new AccountPanel();
		this.logo = new LogoLabel(LogoLabel.BIG);
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		JPanel panelLogo = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelTop.setOpaque(false);
		this.panelAcc.setAccount(person);
		this.panelAcc.setStrokeSize(0);
		panelLogo.setOpaque(false);
		
		///Add to panel
		//Add to panelLogo
		Gbm.goToOrigin(c);
		panelLogo.add(this.logo, c);		//Logo
		Gbm.newGridLine(c);
		panelLogo.add(this.logoDesc, c);	//Logo description
		//Add to panelTop
		this.panelTop.add(this.panelAcc, BorderLayout.NORTH);
		this.panelTop.add(panelLogo, BorderLayout.CENTER);
	}
	private void initPanelCenter(Person person, boolean asAdmin)
	{
		//Initialization
		this.panelCenter = new JPanel(new BorderLayout());
		if (!this.asAdmin) 
		{
			this.initMenuUser(person);
		}
		else 
		{
			this.initMenuAdmin(person);	
		}
		
		//Properties
		this.panelCenter.setOpaque(false);
		
	}
	private void initMenuAdmin(Person person)
	{
		//Initialization
		this.butHistory = new AppButton("History", Constants.ICON_HISTORY);
		this.butTransfer = new AppButton("Transfer", Constants.ICON_TRANSFER);
		this.butReport = new AppButton("Report", Constants.ICON_REPORT);
		this.butCategories = new AppButton("Categories", Constants.ICON_CATEGORIES);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties 
		panel.setOpaque(false);
		
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
		this.butCategories.setForeground(Color.BLACK);
		this.butCategories.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				//Main.pushScreen(new CategoryEditor(admin));
			}
		});
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = new Insets(10, 30, 10, 30);
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(this.butTransfer, c);				//Transfer button
		Gbm.nextGridColumn(c);
		panel.add(this.butHistory, c);				//History button
		Gbm.nextGridColumn(c);
		panel.add(this.butReport, c);				//Report button
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		c.insets = new Insets(30, 30, 10, 30);
		panel.add(this.butCategories, c);			//Categories button
		this.panelCenter.add(panel, BorderLayout.CENTER);
	}
	private void initMenuUser(Person person) {
		this.butHistory = new AppButton("History", Constants.ICON_HISTORY);
		this.butInput = new AppButton("Input", Constants.ICON_INPUT);
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties 
		panel.setOpaque(false);
		
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
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = new Insets(10, 30, 10, 30);
		c.fill = GridBagConstraints.VERTICAL;
		panel.add(this.butInput, c);				//Transfer button
		Gbm.nextGridColumn(c);
		panel.add(this.butHistory, c);				//History button
		this.panelCenter.add(panel, BorderLayout.CENTER);
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
		frame.setSize(1280, 768);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(mn);
		
		frame.setVisible(true);
	}
}
