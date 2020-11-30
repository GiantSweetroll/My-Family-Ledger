package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import shared.screens.NameEmailPanel;
import shared.screens.RoundedPanel;
import shared.screens.TriplePanelPage;

public class TransferFunds extends TriplePanelPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5419686958065914807L;
	
	//Fields
	private Person person;
	private AccountPanel panelAcc;
	private JPanel panelReceivers, 
					panelTransfer, 
					panelPrev;
	private JLabel labReceiverHeader,
					labTransfer, 
					labPrev,
					labClickSelect,
					labReceiver,
					labLastTf,
					labBack;
	private JButton butTf;
	private NameEmailPanel chosen;
	private JTextField tfAmount, tfNotes;
	
	//Constructor
	public TransferFunds(Person person)
	{
		super();
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelTransfer();
		this.initPanelReceivers();
		this.initPanelPrev();
		
		//Properties
		this.setCenterPanels(this.panelReceivers, this.panelTransfer, this.panelPrev);
		this.setPanelTop(this.panelAcc);
	}

	//Private methods
	private void initPanelTransfer()
	{
		//Initialization
		this.panelTransfer = new RoundedPanel(false);
		this.labTransfer = new JLabel("Transfer");
		this.labReceiver = new JLabel("Receiver");
		this.chosen = new NameEmailPanel("", "");
		this.tfAmount = new JTextField(10);
		this.tfNotes = new JTextField(10);
		this.labBack = new JLabel("Back");
		this.butTf = new JButton("Transfer");
		JPanel panelButtons = new JPanel(new GridLayout(0, 2));
		JPanel panelInput = new JPanel();
		JPanel panelContent = new JPanel(new GridBagLayout());
		JPanel panelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelTransfer.setLayout(new BorderLayout(5, 50));
		this.panelTransfer.setBackground(Color.WHITE);
		this.labTransfer.setFont(Constants.FONT_TITLE);
		this.labTransfer.setHorizontalAlignment(SwingConstants.CENTER);
		this.labBack.setFont(Constants.FONT_SMALLER);
		this.labBack.setForeground(Constants.COLOR_HYPERLINK);
		this.labBack.setHorizontalAlignment(SwingConstants.CENTER);
		this.butTf.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butTf.setForeground(Color.WHITE);
		panelButtons.setBackground(Color.WHITE);
		panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));
		panelInput.setBackground(Color.WHITE);
		panelContent.setBackground(Color.WHITE);
		panelCenter.setOpaque(false);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		
		///Add to panel
		//Add to panelButtons
		panelButtons.add(this.labBack);
		panelButtons.add(this.butTf);
		//Add to panelInput
		panelInput.add(this.labReceiver);
		panelInput.add(Box.createRigidArea(new Dimension(0, 20)));
		panelInput.add(this.chosen);
		panelInput.add(Box.createRigidArea(new Dimension(0, 20)));
		panelInput.add(this.tfAmount);
		panelInput.add(Box.createRigidArea(new Dimension(0, 20)));
		panelInput.add(this.tfNotes);
		panelInput.add(Box.createRigidArea(new Dimension(0, 50)));
		//Add to panelContent
		Gbm.goToOrigin(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = Constants.INSETS_GENERAL;
		panelContent.add(panelInput, c);			//panelInput
		Gbm.newGridLine(c);
		panelContent.add(panelButtons, c);			//panelButtons
		//Add to panelCenter
		panelCenter.add(panelContent);
		//Add to panelTransfer
		this.panelTransfer.add(this.labTransfer, BorderLayout.NORTH);
		this.panelTransfer.add(panelCenter, BorderLayout.CENTER);
		this.panelTransfer.add(panelRight, BorderLayout.EAST);
		this.panelTransfer.add(panelLeft, BorderLayout.WEST);
	}
	private void initPanelReceivers()
	{
		//Initialization
		this.panelReceivers = new RoundedPanel(false);
		this.labReceiverHeader = new JLabel("Receivers");
		this.labClickSelect = new JLabel("Click to select");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelReceivers.setLayout(new BorderLayout(5, 50));
		this.panelReceivers.setBackground(Color.WHITE);
		this.labReceiverHeader.setFont(Constants.FONT_SUB_TITLE);
		this.labReceiverHeader.setHorizontalAlignment(SwingConstants.CENTER);
		this.labClickSelect.setFont(Constants.FONT_SMALLER);
		this.labClickSelect.setHorizontalAlignment(SwingConstants.CENTER);
		this.labClickSelect.setForeground(Constants.COLOR_TEXT_GRAY);
		panelTop.setOpaque(false);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labReceiverHeader, BorderLayout.NORTH);
		panelTop.add(this.labClickSelect, BorderLayout.SOUTH);
		//Add to panelRecievers
		this.panelReceivers.add(panelTop, BorderLayout.NORTH);
	}
	private void initPanelPrev()
	{
		//Initialization
		this.panelPrev = new RoundedPanel(false);
		this.labPrev = new JLabel ("Previous Transfers");
		this.labLastTf = new JLabel ("Your last 5 transfers");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelPrev.setBackground(Color.WHITE);
		this.labPrev.setFont(Constants.FONT_SUB_TITLE);
		this.labPrev.setHorizontalAlignment(SwingConstants.CENTER);
		this.labLastTf.setFont(Constants.FONT_SMALLER);
		this.labLastTf.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labLastTf.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		///Add to panel
		//add to panelTop
		panelTop.add(this.labPrev, BorderLayout.NORTH);
		panelTop.add(this.labLastTf, BorderLayout.SOUTH);
		//Add to panelPrev
		this.panelPrev.add(panelTop, BorderLayout.NORTH);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		TransferFunds tf = new TransferFunds(null);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(tf);
		
		frame.setVisible(true);
	}
}
