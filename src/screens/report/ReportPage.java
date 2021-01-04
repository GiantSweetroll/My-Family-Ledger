package screens.report;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Admin;
import models.Category;
import models.Person;
import models.Transaction;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.AppButton;
import shared.components.DatePicker;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
import shared.components.tables.SimpleTable;
import shared.screens.AccountPanel;
import shared.screens.RoundedPanel;

public class ReportPage extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6092712915841075799L;
	
	//Fields
	private JPanel panelTop, panelBelow,
					panelTableTop, panelTableBelow,
					panelUsers, panelUsersComp,
					panelDate, panelDateComp;
	private AppButton butBack,
						butRefresh,
						butResetDate;
	private JLabel dateLabel, 
					usersLabel, 
					labFrom, 
					labTo;
	private DatePicker dateFrom, dateTo;
	private AccountPanel panelAcc;
	private List<ListTile> tilesUsers;
	private ListView lvUsers;
	private SimpleTable tableTrans, tableSum;
	private JScrollPane scrollTableTop, 
						scrollTableBottom, 
						scrollUsers;
	//Constants
	private static final String[] HEADERS_TRANSACTIONS = {"Date", 
															"Category", 
															"Name",
															"Amount (Rp.)", 
															"Receipt Link", 
															"Last Modified"};
	private static final String[] HEADERS_SUM = {"First Name", 
												"Last Name", 
												"Email", 
												"Total Income (Rp.)",
												"Total Expenditure (Rp.)", 
												"Balance (Rp.)"};
	
	//Constructor
	public ReportPage(Person person)
	{
		//Initialization
		this.panelAcc = new AccountPanel();
		this.initPanelTop();
		this.initPanelBelow();
		this.initDatePanel();
		this.initPanelUsers();
		this.initPanelTableTop();
		this.initPanelTableBelow();
		
		JPanel panelLeft = new JPanel(new GridLayout(2,0));
		JPanel panelRight = new JPanel(new GridLayout(2,0));
		JPanel panelBottom = new JPanel(new BorderLayout(10, 0));
		JPanel panelContent = new JPanel(new BorderLayout());
		JPanel panelContentBorder = new JPanel(new BorderLayout(10,0));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		//Properties
		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.GRAY);
		this.panelAcc.setAccount(person);
		panelBottom.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		panelContent.setOpaque(false);
		panelContentBorder.setOpaque(false);
		
		//Add to panelBottom
		panelBottom.add(this.panelBelow,BorderLayout.CENTER);
		panelBottom.add(p1,BorderLayout.EAST);
		panelBottom.add(p2,BorderLayout.SOUTH);
		panelBottom.add(p3,BorderLayout.WEST);
		//Add to panelLeft
		panelLeft.add(this.panelUsers);
		panelLeft.add(this.panelDate);
		//Add to panelRight
		panelRight.add(this.panelTableTop);
		panelRight.add(this.panelTableBelow);
		//Add to panelContent
		panelContent.add(panelLeft,BorderLayout.WEST);
		panelContent.add(panelRight,BorderLayout.CENTER);
		//PanelContentBorder
		panelContentBorder.add(panelContent,BorderLayout.CENTER);
		panelContentBorder.add(p4,BorderLayout.EAST);
		panelContentBorder.add(p5,BorderLayout.WEST);
		//Add to panel 
		this.add(this.panelTop,BorderLayout.NORTH);
		this.add(panelBottom,BorderLayout.SOUTH);
		this.add(panelContentBorder,BorderLayout.CENTER);
	}
	
	//Public methods
	public void updateListViewUsers()
	{
		int adminID = Globals.activeUser.getID();
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
	
	//Private methods
	//UI Initialization
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		//Properties
		this.panelTop.setLayout(new BorderLayout(10, 0));
		this.panelTop.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		
		//Add to panel
		this.panelTop.add(p1, BorderLayout.NORTH);
		this.panelTop.add(p2, BorderLayout.WEST);
		this.panelTop.add(this.panelAcc, BorderLayout.CENTER);
		this.panelTop.add(p3, BorderLayout.EAST);
	}
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new RoundedPanel(false);
		this.butBack = new AppButton("Back");
		this.butRefresh = new AppButton("Refresh");
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
		//Properties
		this.panelBelow.setBackground(Color.WHITE);
		this.panelBelow.setLayout(new BorderLayout(10,0));
		this.panelBelow.setOpaque(false);
		this.butBack.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butBack.setForeground(Color.WHITE);
		this.butBack.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						Main.changeScreen(new Menu(Globals.activeUser, true));
					}
				});
		this.butRefresh.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butRefresh.setForeground(Color.WHITE);
		this.butRefresh.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						updateTableTrans();
						updateTableSum();
					}
				});
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
	
		///Add to panel
		//add to panelLeft
		panelLeft.add(this.butBack);
		//Add to panelRight
		panelRight.add(this.butRefresh);
		
		//Add to panelButtons
		this.panelBelow.add(panelLeft, BorderLayout.WEST);
		this.panelBelow.add(panelRight, BorderLayout.EAST);
	}
	private void initPanelTableTop()
	{
		//Initialization
		this.panelTableTop = new RoundedPanel(false);
		this.tableTrans = new SimpleTable(null, HEADERS_TRANSACTIONS);
		this.scrollTableTop = ScrollPaneManager.generateDefaultScrollPane(this.tableTrans, 10, 10);
		
		//Properties
		this.panelTableTop.setLayout(new BorderLayout());
		this.panelTableTop.setBackground(Color.WHITE);
		this.scrollTableTop.setBorder(null);
		this.scrollTableTop.getViewport().setOpaque(false);
		this.scrollTableTop.setOpaque(false);
		this.updateTableTrans();
		
		//Add to panel
		this.panelTableTop.add(this.scrollTableTop);
	}
	private void initPanelTableBelow()
	{
		this.panelTableBelow = new RoundedPanel(false);
		this.tableSum = new SimpleTable(null, HEADERS_SUM);
		this.scrollTableBottom = ScrollPaneManager.generateDefaultScrollPane(this.tableSum, 10, 10);;
		
		//Properties
		this.panelTableBelow.setLayout(new BorderLayout());
		this.panelTableBelow.setBackground(Color.WHITE);
		this.scrollTableBottom.setBorder(null);
		this.scrollTableBottom.getViewport().setOpaque(false);
		this.scrollTableBottom.setOpaque(false);
		this.updateTableSum();
		
		//Add to panel
		this.panelTableBelow.add(this.scrollTableBottom);
	}
	private void initPanelUsers() 
	{
		this.panelUsers = new RoundedPanel(false);
		this.panelUsersComp = new JPanel();
		this.usersLabel = new JLabel("Users");
		this.lvUsers = new ListView();
		this.scrollUsers = ScrollPaneManager.generateDefaultScrollPane(this.lvUsers, 10, 10);
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		SpringLayout spr = new SpringLayout();
		JPanel paneltop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelUsers.setBackground(Color.WHITE);
		this.panelUsersComp.setLayout(spr);
		this.panelUsersComp.setOpaque(false);
		this.panelUsers.setLayout(new BorderLayout(10, 0));
		this.usersLabel.setFont(Constants.FONT_GENERAL_BOLD);
		this.usersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.lvUsers.setMultipleSelection(false);
		this.scrollUsers.getViewport().setOpaque(false);
		this.scrollUsers.setOpaque(false);
		this.scrollUsers.getViewport().setBorder(null);
		this.scrollUsers.setBorder(null);
		paneltop.setOpaque(false);
		updateListViewUsers();
		
		//SpringLayout constraints
		spr.putConstraint(SpringLayout.NORTH, this.scrollUsers, 5, SpringLayout.NORTH, this.panelUsersComp);
		spr.putConstraint(SpringLayout.SOUTH, this.scrollUsers, -10, SpringLayout.SOUTH, this.panelUsersComp);
		spr.putConstraint(SpringLayout.WEST, this.scrollUsers, 10, SpringLayout.WEST, this.panelUsersComp);
		spr.putConstraint(SpringLayout.EAST, this.scrollUsers, -10, SpringLayout.EAST, this.panelUsersComp);
		spr.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.scrollUsers, 0, SpringLayout.HORIZONTAL_CENTER, this.panelUsersComp);
		
		//Add to panel 
		//add to panel Top
		paneltop.add(usersLabel,BorderLayout.NORTH);
		paneltop.add(sep,BorderLayout.CENTER);
		//Add to panelCenter
		this.panelUsersComp.add(this.scrollUsers);
		//Add to panelUsers
		this.panelUsers.add(paneltop,BorderLayout.NORTH);
		this.panelUsers.add(this.panelUsersComp, BorderLayout.CENTER);
	}
	private void initDatePanel() 
	{
		this.panelDate = new RoundedPanel(false);
		this.panelDateComp = new JPanel(new GridBagLayout());
		this.dateLabel = new JLabel("Date Range");
		this.labFrom = new JLabel("From");
		this.labTo = new JLabel("To");
		this.dateFrom = new DatePicker();
		this.dateTo = new DatePicker();
		this.butResetDate = new AppButton("Reset");
		JPanel pbutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelDate.setLayout(new BorderLayout(10, 0));
		this.panelDate.setBackground(Color.WHITE);
		this.panelDateComp.setOpaque(false);
		this.dateLabel.setFont(Constants.FONT_GENERAL_BOLD);
		this.dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.butResetDate.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butResetDate.setForeground(Color.WHITE);
		this.butResetDate.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						dateFrom.resetDefaults();
						dateTo.resetDefaults();
						deselectAllReceivers();
					}
				});
		panelDateComp.setOpaque(false);
		pbutton.setOpaque(false);
		
		//Add to panel 
		//add to panel1 
		pbutton.add(butResetDate);
		//Add to panelDateComp
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		this.panelDateComp.add(this.labFrom, c);				//From label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.panelDateComp.add(this.dateFrom, c);				//From Date
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		this.panelDateComp.add(this.labTo, c);					//To label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.panelDateComp.add(this.dateTo, c);					//To Date
		Gbm.newGridLine(c);
		//add to panelDate
		this.panelDate.add(this.dateLabel,BorderLayout.NORTH);
		this.panelDate.add(this.panelDateComp, BorderLayout.CENTER);
		this.panelDate.add(pbutton,BorderLayout.SOUTH);	
	}
	//Operations
	private void updateTableSum()
	{
		int adminID = Globals.activeUser.getID();
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers(adminID);
		
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
		this.tableSum.updateData(currentData, HEADERS_SUM);
	}
	private void updateTableTrans()
	{
		try
		{
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
			this.tableTrans.updateData(filteredTransactions, HEADERS_TRANSACTIONS);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		Admin admin = new Admin(1, "Gardyan","Akbar");
		Globals.activeUser = admin;
		ReportPage rp = new ReportPage(admin);
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(rp);
		
		frame.setVisible(true);
	}
}
