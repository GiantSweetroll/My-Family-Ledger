package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import models.Account;
import models.Admin;
import models.Person;
import models.Transaction;
import models.User;
import shared.Constants;
import shared.Methods;
import shared.components.HintTextField;
import shared.components.NameEmailPanel;
import shared.components.WarningLabel;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
import shared.components.listview.TransactionTile;
import shared.screens.AccountPanel;
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
					labBack,
					labWarning;
	private JButton butTf;
	private NameEmailPanel chosen;
	private JTextField tfAmount, tfNotes;
	private ListView lvReceivers,
					lvPrevTransfers;
	private List<ListTile> tilesReceivers,
						tilesPrevTransfers;
	private JScrollPane scrollReceiver,
						scrollPrevTransfer;
	private Runnable receiverChecker;
	private Thread receiverThread;
	
	//Constants
	private final String RECEIVER_NOT_SELECTED = "<html><i>No receivers chosen yet</i></html>",
						RECEIVER_NOT_SELECTED_SUB = "<html><i>Please select from the panel on the left</i></html>";
	
	//Constructor
	public TransferFunds(Person person)
	{
		super();
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.receiverChecker = new Runnable()
				{
					public void run()
					{
						try
						{
							ListTile tile = lvReceivers.getSelectedTiles().get(0);
							Person person = ((SimpleUserTile) tile).getPerson();
							chosen.setNameEmail(person);
						}
						catch(NullPointerException ex)
						{
							chosen.setNameEmail(RECEIVER_NOT_SELECTED, RECEIVER_NOT_SELECTED_SUB);
						}
					}
				};
		this.receiverThread = new Thread(this.receiverChecker);
		this.initPanelTransfer();
		this.initPanelReceivers();
		this.initPanelPrev();
		
		//Properties
		this.panelAcc.setAccount(this.person);
		this.setCenterPanels(this.panelReceivers, this.panelTransfer, this.panelPrev);
		this.setPanelTop(this.panelAcc);
		
		//Thread
		Thread tfThread = new Thread(new Runnable()
				{
					public void run()
					{
						while(true)
						{
							if (!receiverThread.isAlive())
							{
								receiverThread = new Thread(receiverChecker);
								receiverThread.start();
							}
						}
					}
				});
		tfThread.start();
	}

	//Private methods
	private void initPanelTransfer()
	{
		//Initialization
		this.panelTransfer = new RoundedPanel(false);
		this.labTransfer = new JLabel("Transfer");
		this.labReceiver = new JLabel("Receiver");
		this.chosen = new NameEmailPanel(this.RECEIVER_NOT_SELECTED, this.RECEIVER_NOT_SELECTED_SUB);
		this.tfAmount = new HintTextField("Amount (Rp.)");
		this.tfNotes = new HintTextField("Notes");
		this.labWarning = new WarningLabel();
		this.labBack = new JLabel("Back");
		this.butTf = new JButton("Transfer");
		SpringLayout spr = new SpringLayout();
		JPanel panelCenter = new JPanel(spr);
		
		//Properties
		this.panelTransfer.setLayout(new BorderLayout());
		this.panelTransfer.setBackground(Color.WHITE);
		this.labTransfer.setFont(Constants.FONT_TITLE);
		this.labTransfer.setHorizontalAlignment(SwingConstants.CENTER);
		this.tfAmount.setColumns(10);
		this.tfNotes.setColumns(10);
		this.labWarning.setHorizontalAlignment(SwingConstants.CENTER);
		this.labBack.setFont(Constants.FONT_SMALLER);
		this.labBack.setForeground(Constants.COLOR_HYPERLINK);
		this.labBack.setHorizontalAlignment(SwingConstants.CENTER);
		this.butTf.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butTf.setForeground(Color.WHITE);
		this.butTf.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{ 
				try
				{
					ListTile selectedTiles = lvReceivers.getSelectedTiles().get(0);
					Person userPerson = ((SimpleUserTile) selectedTiles).getPerson();
					User user = Constants.DATABASE_SERVICE.getUser(userPerson.getID());
					
					//Make transaction
					int categoryId = 1; //"Transfer" is a hardcoded category for 1
					int adminId = 1; //person.getID();
					int userId = user.getID();
					long millis = System.currentTimeMillis(); 
					Date date = new java.sql.Date(millis);
					double amount = Double.parseDouble(tfAmount.getText());
					String notes = tfNotes.getText();
					Transaction trans = new Transaction(categoryId, adminId, userId, date, date, amount, notes);
					Constants.DATABASE_SERVICE.insert(trans);
					
					//Update the account balance
					Account userAccount = Constants.DATABASE_SERVICE.getAccount(user.getAccountID());
					userAccount.updateBalance(amount);
					Constants.DATABASE_SERVICE.update(userId, userAccount);
					
					//Reset
					updateListViewReceivers();
					resetFields();
				}
				catch(NumberFormatException ex)
				{
					labWarning.setText("Invalid value for amount");
				}
				catch(NullPointerException ex)
				{
					labWarning.setText("Please select a receiver");
				}
			}  
		});
		panelCenter.setOpaque(false);
		
		///SpringLayout Constraints
		int sidePadding = 70;	//Left and right padding
		int tbPadding = 20;		//Top bottom padding
		//labReceiver constraints
		spr.putConstraint(SpringLayout.WEST, this.labReceiver, sidePadding, SpringLayout.WEST, panelCenter);
		//chosen constraints
		spr.putConstraint(SpringLayout.WEST, this.chosen, sidePadding, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.NORTH, this.chosen, tbPadding, SpringLayout.SOUTH, this.labReceiver);
		spr.putConstraint(SpringLayout.EAST, this.chosen, -1 * sidePadding, SpringLayout.EAST, panelCenter);
		//tfAmount constraints
		spr.putConstraint(SpringLayout.WEST, this.tfAmount, sidePadding, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.NORTH, this.tfAmount, tbPadding, SpringLayout.SOUTH, this.chosen);
		spr.putConstraint(SpringLayout.EAST, this.tfAmount, -1 * sidePadding, SpringLayout.EAST, panelCenter);
		//tfNotes constraints
		spr.putConstraint(SpringLayout.WEST, this.tfNotes, sidePadding, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.NORTH, this.tfNotes, tbPadding, SpringLayout.SOUTH, this.tfAmount);
		spr.putConstraint(SpringLayout.EAST, this.tfNotes, -1 * sidePadding, SpringLayout.EAST, panelCenter);
		//labWarning constraints
		spr.putConstraint(SpringLayout.WEST, this.labWarning, sidePadding, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.NORTH, this.labWarning, tbPadding, SpringLayout.SOUTH, this.tfNotes);
		spr.putConstraint(SpringLayout.EAST, this.labWarning, -1 * sidePadding, SpringLayout.EAST, panelCenter);
		//butTf constraints
		spr.putConstraint(SpringLayout.NORTH, this.butTf, 80, SpringLayout.SOUTH, this.labWarning);
		spr.putConstraint(SpringLayout.WEST, this.butTf, 5, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		spr.putConstraint(SpringLayout.EAST, this.butTf, -1 * sidePadding, SpringLayout.EAST, panelCenter);
		//labBack constraints
		spr.putConstraint(SpringLayout.NORTH, this.labBack, 80, SpringLayout.SOUTH, this.labWarning);
		spr.putConstraint(SpringLayout.EAST, this.labBack, -5, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		spr.putConstraint(SpringLayout.WEST, this.labBack, sidePadding, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.VERTICAL_CENTER, this.labBack, 0, SpringLayout.VERTICAL_CENTER, this.butTf);
		
		///Add to panel
		//Add to panelCenter
		panelCenter.add(this.labReceiver);
		panelCenter.add(this.chosen);
		panelCenter.add(this.tfAmount);
		panelCenter.add(this.tfNotes);
		panelCenter.add(this.labWarning);
		panelCenter.add(this.labBack);
		panelCenter.add(this.butTf);
		//Add to panelTransfer
		this.panelTransfer.add(this.labTransfer, BorderLayout.NORTH);
		this.panelTransfer.add(panelCenter, BorderLayout.CENTER);
	}
	private void initPanelReceivers()
	{
		//Initialization
		this.panelReceivers = new RoundedPanel(false);
		this.labReceiverHeader = new JLabel("Receivers");
		this.labClickSelect = new JLabel("Click to select");
		this.lvReceivers = new ListView();
		this.scrollReceiver = ScrollPaneManager.generateDefaultScrollPane(this.lvReceivers, 10, 10);
		SpringLayout spr = new SpringLayout();
		JPanel panelTop = new JPanel(new BorderLayout());
		JPanel panelCenter = new JPanel(spr);
		
		//Properties
		this.panelReceivers.setLayout(new BorderLayout(5, 50));
		this.panelReceivers.setBackground(Color.WHITE);
		this.labReceiverHeader.setFont(Constants.FONT_SUB_TITLE);
		this.labReceiverHeader.setHorizontalAlignment(SwingConstants.CENTER);
		this.labClickSelect.setFont(Constants.FONT_SMALLER);
		this.labClickSelect.setHorizontalAlignment(SwingConstants.CENTER);
		this.labClickSelect.setForeground(Constants.COLOR_TEXT_GRAY);
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		this.lvReceivers.setMultipleSelection(false);
		this.scrollReceiver.getViewport().setOpaque(false);
		this.scrollReceiver.setOpaque(false);
		this.scrollReceiver.getViewport().setBorder(null);
		this.scrollReceiver.setBorder(null);
		updateListViewReceivers();
		
		//SpringLayout constraints
		spr.putConstraint(SpringLayout.WEST, this.scrollReceiver, 20, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.EAST, this.scrollReceiver, -20, SpringLayout.EAST, panelCenter);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.scrollReceiver, 0, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labReceiverHeader, BorderLayout.NORTH);
		panelTop.add(this.labClickSelect, BorderLayout.SOUTH);
		//Add to panelCenter
		panelCenter.add(this.scrollReceiver);
		//Add to panelRecievers
		this.panelReceivers.add(panelTop, BorderLayout.NORTH);
		this.panelReceivers.add(panelCenter, BorderLayout.CENTER);
	}
	private void initPanelPrev()
	{
		//Initialization
		this.panelPrev = new RoundedPanel(false);
		this.tilesPrevTransfers = new ArrayList<>();
		this.labPrev = new JLabel ("Previous Transfers");
		this.labLastTf = new JLabel ("Your last 5 transfers");
		this.lvPrevTransfers = new ListView();
		this.scrollPrevTransfer = new JScrollPane(this.lvPrevTransfers);
		SpringLayout spr = new SpringLayout();
		JPanel panelTop = new JPanel(new BorderLayout());
		JPanel panelCenter = new JPanel(spr);
		
		//Properties
		this.panelPrev.setBackground(Color.WHITE);
		this.labPrev.setFont(Constants.FONT_SUB_TITLE);
		this.labPrev.setHorizontalAlignment(SwingConstants.CENTER);
		this.labLastTf.setFont(Constants.FONT_SMALLER);
		this.labLastTf.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labLastTf.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		this.lvPrevTransfers.setMultipleSelection(false);
		this.scrollPrevTransfer.getViewport().setOpaque(false);
		this.scrollPrevTransfer.setOpaque(false);
		this.scrollPrevTransfer.getViewport().setBorder(null);
		this.scrollPrevTransfer.setBorder(null);
		updateListViewPrevTransfers();
		
		//SpringLayout Constraints
		spr.putConstraint(SpringLayout.WEST, this.scrollReceiver, 20, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.EAST, this.scrollReceiver, -20, SpringLayout.EAST, panelCenter);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.scrollReceiver, 0, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		
		///Add to panel
		//add to panelTop
		panelTop.add(this.labPrev, BorderLayout.NORTH);
		panelTop.add(this.labLastTf, BorderLayout.SOUTH);
		//add to panelCenter
		panelCenter.add(this.scrollPrevTransfer);
		//Add to panelPrev
		this.panelPrev.add(panelTop, BorderLayout.NORTH);
		this.panelPrev.add(panelCenter, BorderLayout.CENTER);
	}
	
	private void updateListViewReceivers() {
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers();
		List<Account> accounts = Constants.DATABASE_SERVICE.getAllAccounts();
		List<ListTile> currentTiles = new ArrayList<ListTile>();
		
		//Add the data
		for(int i=0; i<users.size(); i++) {
			SimpleUserTile sut = new SimpleUserTile(users.get(i));
			sut.setTopRightText("Rp. " + String.valueOf(accounts.get(i).getBalance()));
			currentTiles.add(sut);
		}
		
		this.tilesReceivers = currentTiles;
		this.lvReceivers.updateData(this.tilesReceivers);
	}
	
	/**
	 * Updates the list view for previous transfers. Calls the revalidate() and repaint() methods.
	 */
	private void updateListViewPrevTransfers() 
	{
		List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions(this.person.getID());
		this.tilesPrevTransfers.clear();
		//Create tiles
		for (Transaction tr : transactions)
		{
			TransactionTile tile = new TransactionTile(tr);
			this.tilesPrevTransfers.add(tile);
		}
		
		this.lvPrevTransfers.updateData(this.tilesPrevTransfers);
	}
	
	private void resetFields() {
		this.tfAmount.setText("Amount (Rp.)");
		this.tfNotes.setText("Notes");
		this.tfAmount.setForeground(Color.GRAY);
		this.tfNotes.setForeground(Color.GRAY);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		TransferFunds tf = new TransferFunds(new Admin(1, "Gardyan", "Akbar"));
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(tf);
		
		frame.setVisible(true);
	}
}
