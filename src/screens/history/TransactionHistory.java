package screens.history;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Category;
import models.Person;
import models.Transaction;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.ComboBoxRenderer;
import shared.components.DatePicker;
import shared.components.HintTextField;
import shared.components.WarningLabel;
import shared.components.tables.TransactionHistoryTable;
import shared.screens.HistoryPanel;

public class TransactionHistory extends HistoryPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Fields 
	private Person person;
	private JPanel filters;
	private JLabel labelFrom,
					labelTo,
					labelPrice,
					labelCategory;
	private JComboBox cbCategory,
						cbEquals;
	private HintTextField tfValue;
	private TransactionHistoryTable tableTrans;
	private DatePicker dateFrom, dateTo;
	private DefaultTableModel model;
	private WarningLabel warningLabel;

	//Constructor
	public TransactionHistory(Person person) {
		super(person);
		
		//Initialization
		this.person = person;
		this.initPanelFilters();
		this.initTable();
		this.refreshButtonPressed();
		
		//Properties
		this.setFilterPanel(this.filters);
		this.setTable(this.tableTrans);
	}
	
	//Public Methods
	private void initPanelFilters() {
		//Initialization
		this.filters = new JPanel();
		this.labelFrom = new JLabel("From");
		this.labelTo = new JLabel("To");
		this.labelPrice = new JLabel("Price (Rp.)");
		this.labelCategory = new JLabel("Category");
		this.tfValue = new HintTextField("Value");
		this.warningLabel = new WarningLabel();
		this.dateFrom = new DatePicker();
		this.dateTo = new DatePicker();
		this.cbEquals = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(panelCenter, 10, 10);
		Calendar cal = Calendar.getInstance();

		List <Category> categories = Constants.DATABASE_SERVICE.getAllCategories(0);	//0 for default categories
		categories.addAll(Constants.DATABASE_SERVICE.getAllCategories(((User)Globals.activeUser).getAdminID()));

		this.cbCategory = new JComboBox<Category>();
		this.cbCategory.addItem("--ALL--");
		for(int i = 0; i < categories.size(); i++) {
//			if(categories.get(i).getID() == 1) {
//				continue;
//			}
			cbCategory.addItem(categories.get(i));
			cbCategory.setRenderer(new ComboBoxRenderer(categories.get(i)));
		}
//		cbCategory.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                // Get the source of the component, which is our combo box.
//                JComboBox cbCategory = (JComboBox) event.getSource();
//                // Print the selected items and the action command.
//                Object selected = cbCategory.getSelectedItem();
//                System.out.println("Selected Item  = " + selected);
//            }
//		});

		
		//Properties
		this.filters.setBackground(Color.WHITE);
		this.labelFrom.setFont(Constants.FONT_GENERAL);
		this.labelTo.setFont(Constants.FONT_GENERAL);
		this.labelPrice.setFont(Constants.FONT_GENERAL);
		this.labelCategory.setFont(Constants.FONT_GENERAL);
		this.tfValue.setFont(Constants.FONT_SMALLER);
		this.tfValue.setForeground(Constants.COLOR_TEXT_GRAY);
		this.cbCategory.setFont(Constants.FONT_SMALLER);
		this.cbEquals.setFont(Constants.FONT_SMALLER);
		this.warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.labelFrom.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelTo.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelPrice.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelCategory.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		cal.add(Calendar.MONTH, -1);
		this.dateFrom.setSelectedDate(new Date(cal.getTimeInMillis()));
		
		panelCenter.setOpaque(false);
		scroll.setBorder(null);
		scroll.getViewport().setBorder(null);
		
		//Add to Panels
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		panelCenter.add(this.labelFrom, c);				//From label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelCenter.add(this.dateFrom, c);				//From Date
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		panelCenter.add(this.labelTo, c);				//To label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelCenter.add(this.dateTo, c);				//To Date
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		panelCenter.add(this.labelPrice, c);			//Price label
		Gbm.nextGridColumn(c);
		panelCenter.add(this.cbEquals, c);				//Operand Combo Box
		Gbm.nextGridColumn(c);
		panelCenter.add(this.tfValue, c);				//Value Text field
		Gbm.newGridLine(c);
		panelCenter.add(this.labelCategory, c);			//Category Label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelCenter.add(this.cbCategory, c);			//Category Combo Box
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		panelCenter.add(this.warningLabel, c);			//Warning label
		
		this.filters.add(panelCenter);
	}

	private void initTable(){
		//Initialization
		
		List<Transaction> transactions = new ArrayList <Transaction>();
		this.tableTrans = new TransactionHistoryTable(transactions);
		this.tableTrans.updateData(transactions);
	}
		
	private static boolean isDigit(String inputText) {
	    if (inputText == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(inputText);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		Person person = new Person("Jocelyn", "Thiojaya");
		TransactionHistory th = new TransactionHistory(person);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(th);
		frame.setVisible(true);
	}

	@Override
	public void resetFilters() {
		this.cbCategory.setSelectedIndex(0);
		this.cbEquals.setSelectedIndex(0);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		this.dateFrom.setSelectedDate(new Date(cal.getTimeInMillis()));
		this.dateTo.resetDefaults();
		
		this.tfValue.setText("");
	}

	@Override
	public void backButtonPressed() {
		Globals.activeUser = person;
		Main.changeScreen(new Menu(person, false), Constants.FRAME_MAIN_MENU);
	}

	@Override
	public void refreshButtonPressed() {
		try {
			int catID = this.cbCategory.getSelectedIndex() == 0 ? -1 : ((Category) this.cbCategory.getSelectedItem()).getID();
			String operator = (String) this.cbEquals.getSelectedItem();
			Date dateMin = this.dateFrom.getSelectedDate();
			Date dateMax = this.dateTo.getSelectedDate();
			String price = this.tfValue.getText().trim();
			int flagStr = this.cbEquals.getSelectedIndex();
			this.panelAcc.setAccount(this.person);
			if(price.equals("") || price.equalsIgnoreCase("Value")) {
				List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions(person.getID(), dateMin, dateMax, flagStr, -1, catID);
				this.tableTrans.updateData(transactions);
			}
			else {
				if(!isDigit(price)) {
					this.warningLabel.setText("Invalid input for Price");
				}
				else {
					Double priced = Double.parseDouble(price);
					List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions(person.getID(), dateMin, dateMax, flagStr, priced, catID);
					this.tableTrans.updateData(transactions);
					this.warningLabel.setText("");
				}
			}
			
		}
		catch(NullPointerException ex)
		{
			this.tableTrans.updateData(new ArrayList<Transaction>());
		}
	}
	
	@Override
	public void resetDefaults() {}

	@Override
	public void onDisplayed() 
	{
		this.refreshButtonPressed();
	}
}
