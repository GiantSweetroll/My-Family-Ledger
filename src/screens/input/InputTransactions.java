package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	private JTextField inputItem, inputPrice;
	
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
		this.panelPrevTrans.setLayout(new BorderLayout(5, 50));
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
		JPanel panelContent = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panelContent, BoxLayout.Y_AXIS);
		this.labelInput = new JLabel("Input");
		this.inputItem = new JTextField("Item/Service Name", 10);
		this.inputPrice = new JTextField("Price (Rp.)", 10);

		//make combo box
		String categories[] = { "Select Category", "Food", "Transport", "Household" };
		this.cbCategory = new JComboBox<String>(categories);
		cbCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo box.
                JComboBox cbCategory = (JComboBox) event.getSource();
                // Print the selected items and the action command.
                Object selected = cbCategory.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
            }
		});
		
		//Properties
		this.panelInput.setLayout(new BorderLayout());
		panelContent.setLayout(boxlayout);
		panelContent.setOpaque(false);
		this.panelInput.setBackground(Color.WHITE);
		this.labelInput.setFont(Constants.FONT_SUB_TITLE);
		this.labelInput.setHorizontalAlignment(SwingConstants.CENTER);
		this.inputItem.setFont(Constants.FONT_SMALLER);
		this.inputItem.setForeground(Constants.COLOR_TEXT_GRAY);
		this.inputPrice.setFont(Constants.FONT_SMALLER);
		this.inputPrice.setForeground(Constants.COLOR_TEXT_GRAY);
		this.cbCategory.setFont(Constants.FONT_SMALLER);
		
		//Add to panels
		//panelContent.add(this.labelInput);
		panelContent.add(Box.createRigidArea(new Dimension(0, 40)));
		panelContent.add(this.cbCategory);
		panelContent.add(this.inputItem);
		panelContent.add(this.inputPrice);
		panelContent.add(Box.createRigidArea(new Dimension(0, 550)));
		this.panelInput.add(this.labelInput, BorderLayout.NORTH);
		this.panelInput.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.WEST);
		this.panelInput.add(panelContent, BorderLayout.CENTER);
		this.panelInput.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.EAST);
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
