package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		this.initPanelTransfer();
		
		//Properties
		this.setCenterPanels(null, this.panelTransfer, null);
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
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelTransfer.setLayout(new BorderLayout());
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
		panelCenter.setBackground(Color.WHITE);
		
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
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		panelCenter.add(panelInput, c);				//panelCenter
		Gbm.newGridLine(c);
		panelCenter.add(panelButtons, c);			//panelButtons
		//Add to panelTransfer
		this.panelTransfer.add(this.labTransfer, BorderLayout.NORTH);
		this.panelTransfer.add(panelCenter, BorderLayout.CENTER);
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
