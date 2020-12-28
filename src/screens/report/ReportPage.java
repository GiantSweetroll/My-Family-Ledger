package screens.report;
 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Account;
import models.Person;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.DatePicker;
import shared.components.NameEmailPanel;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
import shared.screens.ReportPanel;

public class ReportPage extends ReportPanel{
	private static final long serialVersionUID = 5307084475379946087L;
	
	//Fields
	
	private Person person;
	private JLabel labFrom, labTo;
	private DatePicker dateFrom, dateTo;
	private NameEmailPanel chosen;
	private List<Person> persons;
	private List<ListTile> tilesUsers;
	private ListView lvUsers;
	private JScrollPane scrollUsers;
	private JTable tableTrans,tableUsers;
	
	String[][] dataTop = {
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "Yesterday"},
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "2 Days Ago"},
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "Yesterday"}
	};
	
	String [][] dataBottom = {
			{"Adam","Smith","adam@gmail.com","150000","20000","90000"}
	};
	String[] columnTopNames = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified"};
	String[] columnBottomNames = {"First Name", "Last Name", "Email", "Total Income (Rp.)",
								"Total Expenditure (Rp.)", "Balance (Rp.)"};
	
	//Constructor
	public ReportPage(Person person) {
		super(person);
		
		//Initialization
		this.person = person;
		this.initDate();
		this.initPanelUsers();
		this.initTableTop();
		this.initTableBottom();
		
		
		//Properties
		this.setTableTop(tableTrans);
		this.setTable2(tableUsers);
		
	}
	
	public void updateListViewUsers()
	{
		
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers();
		List <Account> accounts = Constants.DATABASE_SERVICE.getAllAccounts();
		List <ListTile> currentTiles = new ArrayList<ListTile>();
		
		//Add the data 
		for(int i =0; i < users.size(); i++) {
			SimpleUserTile SUT = new SimpleUserTile(users.get(i));
			currentTiles.add(SUT);	
		}
		this.tilesUsers = currentTiles;
		this.lvUsers.updateData(tilesUsers);
	}
	
	private void initPanelUsers()
	{
		//Initialization
		this.lvUsers = new ListView();
		this.persons = new ArrayList<Person>();
		this.scrollUsers = ScrollPaneManager.generateDefaultScrollPane(this.lvUsers);
		JPanel panelCenter = new JPanel();
		
		//Properties
		this.scrollUsers.getViewport().setOpaque(true);
		this.scrollUsers.setOpaque(false);
		this.scrollUsers.setBorder(null);
		panelCenter.setOpaque(false);
		panelCenter.add(scrollUsers);
		updateListViewUsers();
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
	
	private void initTableTop() {
		TableModel modelTop = new DefaultTableModel(this.dataTop, this.columnTopNames){
			public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
			}
		};
		
		this.tableTrans = new JTable(modelTop);

		//Properties
		this.tableTrans.getTableHeader().setFont(Constants.FONT_SMALLER_BOLD);
		this.tableTrans.setFont(Constants.FONT_SMALLER);
		this.tableTrans.setPreferredScrollableViewportSize(new Dimension(400, 300));
	    this.tableTrans.setFillsViewportHeight(true);
	    this.tableTrans.setRowHeight(30);   
	}
	
	private void initTableBottom() {
		TableModel modell = new DefaultTableModel(this.dataBottom,this.columnBottomNames) {
			public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
			}
		};
		
		//Properties
		this.tableUsers = new JTable(modell);
		this.tableUsers.getTableHeader().setFont(Constants.FONT_SMALLER_BOLD);
		this.tableUsers.setFont(Constants.FONT_SMALLER);
		this.tableUsers.setPreferredScrollableViewportSize(new Dimension(400, 300));
	    this.tableUsers.setFillsViewportHeight(true);
	    this.tableUsers.setRowHeight(30);
		
		
	}
	
	@Override
	public void BackButtonPressed() {
		Globals.activeUser = person;
		Main.changeScreen(new Menu(person,true));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RefreshButtonPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ResetDateButtonPressed() {
		// TODO Auto-generated method stub
		this.dateFrom.resetDefaults();
		this.dateTo.resetDefaults();
		
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


