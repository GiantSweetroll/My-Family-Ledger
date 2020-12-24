package screens.menu;


import java.awt.BorderLayout;
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
import javax.swing.plaf.FontUIResource;

import giantsweetroll.ImageManager;
import models.Person;
import shared.Constants;
import shared.Methods;
import shared.components.AppButton;
import shared.components.LogoLabel;
import shared.screens.AccountPanel;


public class Menu extends JPanel{
	
	private static final long serialVersionUID = -4877032747183645341L;

	//Fields
	private Person person;
	private AppButton butTransfer, 
					butHistory, 
					butReport, butExit,
					butInput, butCategories;
	private AccountPanel panelAcc;
	private LogoLabel logo;
	private JLabel logoDesc, 
					historyIconLabel, historyLabel,
					transferIconLabel, transferLabel,
					reportIconLabel, reportLabel,
					inputIconLabel, inputLabel,
					addCatIconLabel, addCatLabel;
	private boolean asAdmin;
	ImageIcon historyicon = createImageIcon("/resources/history_icon.png", "History");
	ImageIcon transfericon = createImageIcon("/resources/transfer_icon.png", "Transfer");
	ImageIcon reporticon = createImageIcon("/resources/report_icon.png", "Report");
	ImageIcon inputicon = createImageIcon("/resources/input_icon.png", "Input");
	ImageIcon categoriesicon = createImageIcon("/resources/categories_icon.png","Categories");
	
	
	public Menu (Person person, boolean asAdmin)
	{
		super();
		this.person = person;
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
		this.person = person;
		this.butHistory = new AppButton();
		this.butTransfer = new AppButton();
		this.butReport = new AppButton();
		this.butCategories = new AppButton();
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel(LogoLabel.BIG);
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		this.historyIconLabel = new JLabel(historyicon);
		this.historyLabel = new JLabel("History");
		this.transferIconLabel = new JLabel(transfericon);
		this.transferLabel = new JLabel("Transfer");
		this.reportIconLabel = new JLabel(reporticon);
		this.reportLabel = new JLabel("Report");
		this.addCatIconLabel = new JLabel(categoriesicon);
		this.addCatLabel = new JLabel ("   Categories");
		JPanel panelHistoryBtn = new JPanel();
		JPanel panelTransferBtn = new JPanel();
		JPanel panelReportBtn = new JPanel();
		JPanel panelCategoriesBtn = new JPanel();
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel();
		JPanel mainPanel = new JPanel(new BorderLayout());
		JScrollPane scrollpane = new JScrollPane(mainPanel);
		this.panelAcc = new AccountPanel();
		
		//Properties 
		panelHistoryBtn.setLayout(new BoxLayout(panelHistoryBtn,BoxLayout.Y_AXIS));
		panelHistoryBtn.setOpaque(false);
		panelTransferBtn.setLayout(new BoxLayout(panelTransferBtn,BoxLayout.Y_AXIS));
		panelTransferBtn.setOpaque(false);
		panelReportBtn.setLayout(new BoxLayout(panelReportBtn,BoxLayout.Y_AXIS));
		panelReportBtn.setOpaque(false);
		panelCategoriesBtn.setLayout(new BoxLayout(panelCategoriesBtn,BoxLayout.X_AXIS));
		panelCategoriesBtn.setOpaque(false);
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.historyIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.transferIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.transferLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.reportIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.reportLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.butTransfer.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butTransfer.setFont(Constants.FONT_GENERAL_BOLD);
		this.butTransfer.setPreferredSize(new Dimension(220,150));
		this.butTransfer.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				
			}
		});
		
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		this.butHistory.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("");
			}
		});
		
		this.butReport.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butReport.setFont(Constants.FONT_GENERAL_BOLD);
		this.butReport.setPreferredSize(new Dimension(220,150));
		this.butReport.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("");
			}
		});
		
		this.butCategories.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butCategories.setFont(Constants.FONT_GENERAL_BOLD);
		this.butCategories.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.butCategories.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("");
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
		
		//Add to History Button
		panelHistoryBtn.add(historyIconLabel);
		panelHistoryBtn.add(historyLabel);
		butHistory.add(panelHistoryBtn);
		
		//Add to Transfer Button
		panelTransferBtn.add(transferIconLabel);
		panelTransferBtn.add(transferLabel);
		butTransfer.add(panelTransferBtn);
		
		//Add to Report Button
		panelReportBtn.add(reportIconLabel);
		panelReportBtn.add(reportLabel);
		butReport.add(panelReportBtn);
		
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
		this.person = person;
		this.butHistory = new AppButton();
		this.butInput = new AppButton();
		this.butCategories = new AppButton();
		this.butExit = new AppButton("Exit");
		this.logo = new LogoLabel(LogoLabel.BIG);
		this.logoDesc = new JLabel("Our Funds Motto or smth");
		this.panelAcc = new AccountPanel();
		this.historyIconLabel = new JLabel(historyicon);
		this.historyLabel = new JLabel("History");
		this.inputIconLabel = new JLabel(inputicon);
		this.inputLabel = new JLabel("Input");
		
		JPanel panelHistoryBtn = new JPanel();
		JPanel panelInputBtn = new JPanel();
		JPanel panelTop = new JPanel();
		JPanel panelButton = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelBottom = new JPanel(); 
		JPanel mainPanel = new JPanel();
		JPanel panelCategories = new JPanel();
		JScrollPane scrollpane = new JScrollPane(mainPanel);
		
		
		//Properties 
		panelHistoryBtn.setLayout(new BoxLayout(panelHistoryBtn,BoxLayout.Y_AXIS));
		panelHistoryBtn.setOpaque(false);
		panelInputBtn.setLayout(new BoxLayout(panelInputBtn,BoxLayout.Y_AXIS));
		panelInputBtn.setOpaque(false);
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,200,200));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelCategories.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setLayout(new BorderLayout());
		this.logoDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.historyIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.inputIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.butInput.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butInput.setFont(Constants.FONT_GENERAL_BOLD);
		this.butInput.setPreferredSize(new Dimension(220,150));
		this.butHistory.setBackground(Constants.COLOR_BUTTON_YELLOW);
		this.butHistory.setFont(Constants.FONT_GENERAL_BOLD);
		this.butHistory.setPreferredSize(new Dimension(220,150));
		
		this.butExit.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butExit.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		
		
		
		//Add to History Button
		panelHistoryBtn.add(historyIconLabel);
		panelHistoryBtn.add(historyLabel);
		butHistory.add(panelHistoryBtn);
		
		//Add to Input Button
		panelInputBtn.add(inputIconLabel);
		panelInputBtn.add(inputLabel);
		butInput.add(panelInputBtn);
		
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
