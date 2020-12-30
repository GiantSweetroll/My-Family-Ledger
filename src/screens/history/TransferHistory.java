package screens.history;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import main.Main;
import models.Account;
import models.Admin;
import models.Person;
import models.Transaction;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Methods;
import shared.components.DatePicker;
import shared.components.HintTextField;
import shared.components.WarningLabel;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
import shared.components.tables.TransferHistoryTable;
import shared.screens.HistoryPanel;

public class TransferHistory extends HistoryPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5307084475379946087L;
	//Fields
	private JLabel labValue, 
					labReceiver,
					labFrom,
					labTo,
					labWarning;
	private JComboBox<String> comboOperand;
	private JTextField tfValue;
	private JScrollPane scrollReceiver;
	private List<Person> persons;
	private Person person;
	private List<ListTile> tiles;
	private DatePicker dateFrom, dateTo;
	private ListView listView;
	private JTable tableTransfer;
	
	//Constructor
	public TransferHistory(Person person)
	{
		//Initialization
		super(person);
		this.person = person;
		this.initFilters();
		this.refreshButtonPressed();
	}
	
	//Public methods
	/**
	 * Update and rebuild the list of selectable receivers. The revalidate() and repaint() methods will be called.
	 * @param persons
	 */
	public void updateReceivers(List<Person> persons)
	{
		//Clear data
		this.tiles.clear();
		this.persons.clear();
		
		//Add data
		this.persons.addAll(persons);
		for (Person person : this.persons)
		{
			SimpleUserTile tile = new SimpleUserTile(person);
			this.tiles.add(tile);
		}
		this.listView.updateData(this.tiles);
		
		this.revalidate();
		this.repaint();
	}
	/**
	 * Disables all selected receivers.
	 */
	public void deselectAllReceivers()
	{
		for (ListTile tile : this.tiles)
		{
			if (tile instanceof SimpleUserTile)
			{
				((SimpleUserTile)tile).setSelected(false);
			}
		}
	}
	
	//Overridden Methods
	@Override
	public void resetFilters()
	{
		this.dateFrom.resetDefaults();
		this.dateTo.resetDefaults();
		this.comboOperand.setSelectedIndex(0);
		this.deselectAllReceivers();
		this.tfValue.setText("");
	}
	
	//Private methods
	private void initPanelReceivers()
	{
		//Initialization
		this.listView = new ListView();
		this.tiles = new ArrayList<ListTile>();
		this.scrollReceiver = new JScrollPane(this.listView);
		
		//Properties
		this.listView.setMultipleSelection(false);
		this.scrollReceiver.getViewport().setOpaque(false);
		this.scrollReceiver.setOpaque(false);
		this.scrollReceiver.getViewport().setBorder(null);
		this.scrollReceiver.setBorder(null);
		updateListView();
	}
	private void initFilters()
	{
		//Initialization
		this.initPanelReceivers();
		this.labValue = new JLabel("Value (Rp.)");
		this.comboOperand = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);
		this.tfValue = new HintTextField("Value");
		this.labWarning = new WarningLabel();
		this.labReceiver = new JLabel("Receiver");
		this.labFrom = new JLabel("From");
		this.dateFrom = new DatePicker();
		this.labTo = new JLabel("To");
		this.dateTo = new DatePicker();
		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		JPanel panelTop = new JPanel(new GridBagLayout());
//		JPanel panelBelow = new JPanel();
		JPanel panelFilter = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.tfValue.setColumns(10);
		panelTop.setOpaque(false);
//		panelBelow.setOpaque(false);
//		panelBelow.setLayout(new BoxLayout(panelBelow, BoxLayout.Y_AXIS));
		panelFilter.setOpaque(false);
		
		///Add to panel
		//Add to panelTop
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		panelTop.add(this.labFrom, c);				//From label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelTop.add(this.dateFrom, c);				//From Date
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		panelTop.add(this.labTo, c);				//To label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelTop.add(this.dateTo, c);				//To Date
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		panelTop.add(this.labValue, c);				//Value label
		Gbm.nextGridColumn(c);
		panelTop.add(this.comboOperand, c);			//Operand combobox
		Gbm.nextGridColumn(c);
		panelTop.add(this.tfValue, c);				//Value text field
		Gbm.newGridLine(c);
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelTop.add(this.labWarning, c);			//Warning label
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		panelTop.add(this.labReceiver, c);			//Receivers
		Gbm.newGridLine(c);
		panelTop.add(sep, c);						//Separator
		Gbm.newGridLine(c);
		panelTop.add(this.scrollReceiver, c);		//Receiver scroll pane
		//Add to panelBelow
//		panelBelow.add(this.labReceiver);
//		panelBelow.add(sep);
//		panelBelow.add(this.scrollReceiver);
//		panelBelow.add(Box.createRigidArea(new Dimension(5, 5)));
		//Add to panelFilter
		panelFilter.add(panelTop, BorderLayout.NORTH);
//		panelFilter.add(panelBelow, BorderLayout.SOUTH);
		//Display filter panel
		this.setFilterPanel(panelFilter);
	}
	
	public void initTable(List<Transaction> trans) {
		//Initialization
		
		List<Transaction> transactions = trans; //hrusnya ambil semua transaction sesuai filters
		
		this.tableTransfer = new TransferHistoryTable(transactions);
		//{"Date", "Category", "Name", "Amount (Rp.)", "Last Modified"};
	    
	    this.setTable(this.tableTransfer);
	}
	
	@Override
	public void backButtonPressed() 
	{
		Main.changeScreen(new Menu(this.person, true));
	}

	@Override
	public void refreshButtonPressed() 
	{
		try
		{
			ListTile selectedTiles = listView.getSelectedTiles().get(0);
			Person userPerson = ((SimpleUserTile) selectedTiles).getPerson();
			User user = Constants.DATABASE_SERVICE.getUser(userPerson.getID());
			
			Date dateStart = this.dateFrom.getSelectedDate();
			Date dateEnd =  this.dateTo.getSelectedDate();
			Object operand = this.comboOperand.getSelectedItem();
			String valueStr = this.tfValue.getText();
			double value = -1;
			if (!valueStr.equals("") && !valueStr.equalsIgnoreCase("value"))
			{
				value = Double.parseDouble(valueStr);
			}
			
			int userId = user.getID();
			this.labWarning.setText("");
			
			int op = 0;
			if (operand == "=") {
				op = Constants.DATABASE_SERVICE.EQUAL_TO;
			}
			else if (operand == "<") {
				op = Constants.DATABASE_SERVICE.LESS_THAN;
			}
			else if (operand == "<=") {
				op = Constants.DATABASE_SERVICE.LESS_THAN_EQUAL;
			}
			else if (operand == ">") {
				op = Constants.DATABASE_SERVICE.GREATER_THAN;
			}
			else if (operand == ">=") {
				op = Constants.DATABASE_SERVICE.GREATER_THAN_EQUAL;
			}
			else {
				System.out.println("Operand error");
			}
			
			//System.out.println("Date start: " + dateStart);
			//System.out.println("Date end: " + dateEnd);
			//System.out.println("Operand: " + op);
			//System.out.println("Value: " + value);
			//System.out.println("User id: " + userId);
			
			List<Transaction> currentTrans = Constants.DATABASE_SERVICE.getAllTransactions(userId, dateStart, dateEnd, op, value, 1);
			initTable(currentTrans);
//			System.out.println(currentTrans);
			
		}
		catch(NumberFormatException ex)
		{
			this.labWarning.setText("Please enter a valid value");
		}
		catch(NullPointerException ex)
		{
			initTable(new ArrayList<Transaction>());
		}
	}

	private void updateListView() {
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers(this.person.getID());
		
		this.tiles.clear();
		//Add the data
		for(int i=0; i<users.size(); i++) {
			SimpleUserTile sut = new SimpleUserTile(users.get(i));
			int accountId = users.get(i).getAccountID();
			Account currentAccount = Constants.DATABASE_SERVICE.getAccount(accountId);
			sut.setTopRightText("Rp. " + String.valueOf(currentAccount.getBalance()));
			this.tiles.add(sut);
		}
		
		this.listView.updateData(this.tiles);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		TransferHistory th = new TransferHistory(new Admin(1, "Jocelyn", "Thiojaya"));
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(th);
		
		frame.setVisible(true);
	}
}
