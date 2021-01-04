package legacy;
 

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.plaf.FontUIResource;


import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import legacy.ReportPanel;
import main.Main;
import models.Category;
import models.Person;
import models.Transaction;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.DatePicker;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
import shared.components.tables.SimpleTable;

public class ReportPage extends ReportPanel{
	private static final long serialVersionUID = 5307084475379946087L;
	
	//Fields
	private Person person;
	private JLabel labFrom, labTo;
	private DatePicker dateFrom, dateTo;
	private List<ListTile> tilesUsers;
	private ListView lvUsers;
	private JScrollPane scrollUsers;
	private SimpleTable tableTrans,tableUsers;
	
	String[] columnTopNames = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified"};
	
	//Constructor
	public ReportPage(Person person) {
		super(person);
		
		//Initialization
		this.person = person;
		this.initDate();
		this.initPanelUsers();
		this.initTableTop(null, columnTopNames);
		this.updateTableBottom();
	}
	
	public void updateListViewUsers()
	{
		Globals.activeUser = person;
		int adminID = Constants.DATABASE_SERVICE.getAdminID(person.getEmail());
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers(adminID);
		List <ListTile> currentTiles = new ArrayList<ListTile>();
		
		//Add the data 
		for(int i =0; i < users.size(); i++) {
			SimpleUserTile SUT = new SimpleUserTile(users.get(i));
			currentTiles.add(SUT);	
		}
		this.tilesUsers = currentTiles;
		this.lvUsers.updateData(tilesUsers);
		this.revalidate();
		this.repaint();
	}
	
	public void deselectAllReceivers()
	{
		for (ListTile tile : this.tilesUsers)
		{
			if (tile instanceof SimpleUserTile)
			{
				((SimpleUserTile)tile).setSelected(false);
			}
		}
	}
	

	
	private void initPanelUsers()
	{
		//Initialization
		this.lvUsers = new ListView();
		this.scrollUsers = ScrollPaneManager.generateDefaultScrollPane(this.lvUsers, 10, 10);
		SpringLayout spr = new SpringLayout();
		JPanel panelCenter = new JPanel(spr);
		
		//Properties
		this.lvUsers.setMultipleSelection(false);
		this.scrollUsers.getViewport().setOpaque(false);
		this.scrollUsers.setOpaque(false);
		this.scrollUsers.getViewport().setBorder(null);
		this.scrollUsers.setBorder(null);
		panelCenter.setOpaque(false);
		updateListViewUsers();
		
		//SpringLayout constraints
		spr.putConstraint(SpringLayout.NORTH, this.scrollUsers, 5, SpringLayout.NORTH, panelCenter);
		spr.putConstraint(SpringLayout.SOUTH, this.scrollUsers, -10, SpringLayout.SOUTH, panelCenter);
		spr.putConstraint(SpringLayout.WEST, this.scrollUsers, 10, SpringLayout.WEST, panelCenter);
		spr.putConstraint(SpringLayout.EAST, this.scrollUsers, -10, SpringLayout.EAST, panelCenter);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.scrollUsers, 0, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		
		//Add to Panel 
		//Add to panelCenter
		panelCenter.add(this.scrollUsers);
		
		//Add to main panel
		this.setusersPanel(panelCenter);
	}
	
	
	
	private void initDate() {
		//Initialization
		this.labFrom = new JLabel("From");
		this.labTo = new JLabel("To");
		this.dateFrom = new DatePicker();
		this.dateTo = new DatePicker();
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		panelCenter.setOpaque(false);
		
		
		//Add to PanelCenter
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		panelCenter.add(this.labFrom, c);				//From label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelCenter.add(this.dateFrom, c);				//From Date
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		panelCenter.add(this.labTo, c);				//To label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelCenter.add(this.dateTo, c);				//To Date
		Gbm.newGridLine(c);
		
		this.setDatePanel(panelCenter);	
	}
	
	private void initTableTop(String[][] tabledata, String [] headers) {
		String[][] tableData = tabledata;
		String [] Headers = headers;
		this.tableTrans = new SimpleTable(tableData,Headers); 
	
		this.setTableTop(tableTrans);
	}
		
	
	private void initTableBottom(String[][] tabledata, String [] headers) { 
		String[][] tableData = tabledata;
		String [] Headers = headers;
		this.tableUsers = new SimpleTable(tableData,Headers); 
		
		this.setTable2(tableUsers);
	}
	

	
	private void updateTableBottom() {
		Globals.activeUser = person;
		int adminID = Constants.DATABASE_SERVICE.getAdminID(person.getEmail());
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers(adminID);
		
		String[] columnBottomNames = {"First Name", "Last Name", "Email", "Total Income (Rp.)",
				"Total Expenditure (Rp.)", "Balance (Rp.)"};
		int col = 6;
		int row = users.size();
		String [][] currentData = new String [row][col];
		double totalBalance = 0;
		double totalIncome = 0;
		double totalExpenditure = 0;
		
		for (int i =0 ; i < row; i++) {
			User u = users.get(i);
			currentData[i][0] =  u.getFirstName();
			currentData[i][1] = u.getLastName();
			currentData[i][2] = u.getEmail();
			
			//Replace this with total income and total expenditure.
			totalIncome = Constants.DATABASE_SERVICE.getIncome(u.getID(), adminID);
			currentData[i][3] =  String.valueOf(totalIncome);
			totalExpenditure = Constants.DATABASE_SERVICE.getExpenditure(u.getID());
			currentData[i][4] = String.valueOf(totalExpenditure);
			totalBalance = Constants.DATABASE_SERVICE.getBalance(u.getAccountID());
			currentData[i][5] = String.valueOf(totalBalance);	
		}
		initTableBottom(currentData,columnBottomNames);
		
	}
	

	

	
	@Override
	public void BackButtonPressed() {
		Globals.activeUser = person;
		Main.changeScreen(new Menu(person,true));
		
	}

	@Override
	public void RefreshButtonPressed() {
		try {
			int userId;
			try
			{
				ListTile tile = lvUsers.getSelectedTiles().get(0);
				Person person = ((SimpleUserTile) tile).getPerson();
				userId = person.getID();
			}
			catch(NullPointerException ex)
			{
				userId = -1;
			}
			
			Date dateStart = this.dateFrom.getSelectedDate();
			Date dateEnd =  this.dateTo.getSelectedDate();
			List<Transaction> currentTrans = Constants.DATABASE_SERVICE.getAllTransactionsForAdminReport(userId,dateStart,dateEnd);
			List <Category> categories = Constants.DATABASE_SERVICE.getAllCategories();
			String[] columnTopNames = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified"};
			int row = currentTrans.size();
			int col = 6;
			String [][] filteredTransactions = new String [row][col];
			for (int i = 0 ; i< row ; i++)
			{
				Transaction trans = currentTrans.get(i);
				filteredTransactions[i][0] = String.valueOf(trans.getDateInput());
				filteredTransactions[i][1] = categories.get(trans.getCategoryID()-1).getName();
				filteredTransactions[i][2] = trans.getDesc();
				filteredTransactions[i][3] = String.valueOf(trans.getAmount());
				filteredTransactions[i][4] = trans.getLinkReceipt();
				filteredTransactions[i][5] = String.valueOf(trans.getDateEdit());
			}
			
			initTableTop(filteredTransactions, columnTopNames);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	@Override
	public void ResetDateButtonPressed() {
		this.dateFrom.resetDefaults();
		this.dateTo.resetDefaults();
		this.deselectAllReceivers();
		
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		ReportPage rp = new ReportPage(new Person("Adam", "Smith"));
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(rp);
		
		frame.setVisible(true);
	}

	
}


