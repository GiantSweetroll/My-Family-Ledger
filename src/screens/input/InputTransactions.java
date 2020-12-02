package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import models.Person;
import shared.Constants;
import shared.Methods;
import shared.screens.AccountPanel;
import shared.screens.RoundedPanel;
import shared.screens.TriplePanelPage;

public class InputTransactions extends TriplePanelPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Fields
	private Person person;
	private AccountPanel panelAcc;
	private JPanel panelPrevTrans, 
					panelInput, 
					panelReceipt;
	private JLabel labelPrevTrans,
					labelLastTs,
					labelInput,
					labelReceipt;
	private JComboBox cbCategory;
	
	public InputTransactions(Person person) 
	{
		super();
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelPrevTrans();
		this.initPanelInput();
		this.initPanelReceipt();
		
		//Properties
		this.setCenterPanels(this.panelPrevTrans, this.panelInput, this.panelReceipt);
		this.setPanelTop(this.panelAcc);
	}
		
		
	private void initPanelPrevTrans()
	{
		//Initialization
		this.panelPrevTrans = new RoundedPanel(false);
		this.labelPrevTrans = new JLabel("Previous Transactions");
		this.labelLastTs = new JLabel("Your Last 5 Transactions");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelPrevTrans.setBackground(Color.WHITE);
		this.labelPrevTrans.setFont(Constants.FONT_SUB_TITLE);
		this.labelPrevTrans.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelLastTs.setFont(Constants.FONT_SMALLER);
		this.labelLastTs.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labelLastTs.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		//Add to panel
		//add to panelTop
		panelTop.add(this.labelPrevTrans, BorderLayout.NORTH);
		panelTop.add(this.labelLastTs, BorderLayout.SOUTH);
		//add to panelPrevTrans
		this.panelPrevTrans.add(panelTop, BorderLayout.NORTH);
	}
	
	private void initPanelInput()
	{
		//Initialization
		this.panelInput = new RoundedPanel(false);
		this.labelInput = new JLabel("Input");
		JPanel panelTop = new JPanel(new BorderLayout());
		JPanel panelContent = new JPanel(new BorderLayout());
		
		String categories[] = { "Food", "Transport", "Household" };
		this.cbCategory = new JComboBox(categories);
		
		//Properties
		this.panelInput.setBackground(Color.WHITE);
		this.labelInput.setFont(Constants.FONT_SUB_TITLE);
		this.labelInput.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		panelContent.setOpaque(false);
		
		//Add to panelTop
		panelTop.add(this.labelInput, BorderLayout.NORTH);
		panelContent.add(this.cbCategory, BorderLayout.NORTH);
		//Add to panelInput
		this.panelInput.add(panelTop, BorderLayout.NORTH);
		this.panelInput.add(panelContent, BorderLayout.SOUTH);
	}
	
	private void initPanelReceipt()
	{
		//Initialization
		this.panelReceipt = new RoundedPanel(false);
		this.labelReceipt = new JLabel("Receipt");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelReceipt.setBackground(Color.WHITE);
		this.labelReceipt.setFont(Constants.FONT_SUB_TITLE);
		this.labelReceipt.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		//Add to panelTop
		panelTop.add(this.labelReceipt, BorderLayout.NORTH);
		//Add to panelInput
		this.panelReceipt.add(panelTop, BorderLayout.NORTH);
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		InputTransactions it = new InputTransactions(null);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(it);
		
		frame.setVisible(true);
	}
}
