package legacy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import models.Person;
import shared.Constants;
import shared.Methods;
import shared.screens.AccountPanel;
import shared.screens.RoundedPanel;
import shared.screens.TriplePanelPage;

public class UserInput extends TriplePanelPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6843104624654744167L;
	//Fields
	private Person person;
	private JLabel labInput,
					labPrev,
					labLastTr,
					labRec,
					labBack,
					labAddRec;
	private AccountPanel panelAcc;
	private JPanel panelInput,
					panelPrev,
					panelRec;
	private JComboBox comboCat;
	private JTextField tfItem, tfCost;
	private JLabel receiptImageHolder;
	private JButton butSave;
	
	//Constructor
	public UserInput(Person person)
	{
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelInput();
		this.initPanelPrev();
		
		//Add to panel
		this.setCenterPanels(this.panelPrev, this.panelInput, null);
		this.setPanelTop(this.panelAcc);
	}
	
	//Private methods
	private void initPanelInput()
	{
		//Initialization
		this.panelInput = new RoundedPanel(false);
		this.labInput = new JLabel("Input");
		this.comboCat = new JComboBox();
		this.tfItem = new JTextField(20);
		this.tfCost = new JTextField(20);
		this.labBack = new JLabel("Back");
		this.butSave = new JButton("Save");
		JPanel panelContent = new JPanel(new GridBagLayout());
		JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelButtons = new JPanel(new GridLayout(0, 2));
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelInput.setLayout(new BorderLayout());
		this.panelInput.setBackground(Color.WHITE);
		this.labInput.setHorizontalAlignment(SwingConstants.CENTER);
		this.labInput.setFont(Constants.FONT_TITLE);
		this.labBack.setForeground(Constants.COLOR_HYPERLINK);
		this.labBack.setHorizontalAlignment(SwingConstants.CENTER);
		this.butSave.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSave.setForeground(Color.WHITE);
		panelContent.setOpaque(false);
		panelCenter.setOpaque(false);
		panelButtons.setOpaque(false);
		
		///Add to panel
		//Add to panelButtons
		panelButtons.add(this.labBack);
		panelButtons.add(this.butSave);
		//Add to panelContent
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelContent.add(this.comboCat, c);			//Category Combo Box
		Gbm.newGridLine(c);
		panelContent.add(this.tfItem, c);			//Item Text Field
		Gbm.newGridLine(c);
		panelContent.add(this.tfCost, c);			//Cost Text Field
		//Add to panelCenter
		panelCenter.add(panelContent);
		//Add to panelInput
		this.panelInput.add(this.labInput, BorderLayout.NORTH);
		this.panelInput.add(panelCenter, BorderLayout.CENTER);
		this.panelInput.add(panelButtons, BorderLayout.SOUTH);
	}
	private void initPanelPrev()
	{
		//Initialization
		this.panelPrev = new RoundedPanel(false);
		this.labPrev = new JLabel("Previous Transactions");
		this.labLastTr = new JLabel("Your last 5 transactions");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelPrev.setBackground(Color.WHITE);
		this.labPrev.setFont(Constants.FONT_SUB_TITLE);
		this.labPrev.setHorizontalAlignment(SwingConstants.CENTER);
		this.labLastTr.setFont(Constants.FONT_SMALLER);
		this.labLastTr.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labLastTr.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labPrev, BorderLayout.NORTH);
		panelTop.add(this.labLastTr, BorderLayout.SOUTH);
		//Add to panelPrev
		this.panelPrev.add(panelTop, BorderLayout.NORTH);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		UserInput tf = new UserInput(null);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(tf);
		
		frame.setVisible(true);
	}
}
