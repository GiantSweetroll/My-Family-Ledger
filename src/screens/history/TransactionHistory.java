package screens.history;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import models.Category;
import models.Person;
import models.Transaction;
import models.User;
import screens.menu.Menu;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.screens.HistoryPanel;
import shared.components.DatePicker;
import shared.components.HintTextField;
import shared.components.HistoryTableCellRenderer;
import shared.components.IconCellRenderer;

public class TransactionHistory extends HistoryPanel{
	
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
	private JTextField tfValue;
	private JTable tableTrans;
	private DatePicker dateFrom, dateTo;
	private DefaultTableModel model;
	private int columns = 8;
	
	private String[] columnNames = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified", "Action", "Action"};
	//Constructor
	public TransactionHistory(Person person) {
		super(person);
		
		//Initialization
		this.person = person;
		this.initPanelFilters();
		this.initTable();
		
		//Properties
		this.setFilterPanel(this.filters);
		this.setTable(this.tableTrans);
	}
	
	//Public Methods
	private void initPanelFilters() {
		//Initialization
		this.filters = new JPanel();
		BoxLayout boxlayout = new BoxLayout(this.filters, BoxLayout.Y_AXIS);
		JPanel panelTop = new JPanel(new GridLayout(2,2));
		JPanel panelCenter = new JPanel(new GridLayout(2,3,0,20));
		this.labelFrom = new JLabel("From");
		this.labelTo = new JLabel("To");
		this.labelPrice = new JLabel("Price (Rp.)");
		this.labelCategory = new JLabel("Category");
		this.tfValue = new HintTextField("Value");
		this.dateFrom = new DatePicker();
		this.dateTo = new DatePicker();
		this.cbEquals = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);

		String categories[] = { "Select Item", "Food", "Transport", "Household" }; //perlu diganti with the actual categories
		this.cbCategory = new JComboBox<String>(categories);

		
		//Properties
		this.filters.setLayout(boxlayout);
		this.filters.setBackground(Color.WHITE);
		this.labelFrom.setFont(Constants.FONT_GENERAL);
		this.labelTo.setFont(Constants.FONT_GENERAL);
		this.labelPrice.setFont(Constants.FONT_GENERAL);
		this.labelCategory.setFont(Constants.FONT_GENERAL);
		this.tfValue.setFont(Constants.FONT_SMALLER);
		this.tfValue.setForeground(Constants.COLOR_TEXT_GRAY);
		this.cbCategory.setFont(Constants.FONT_SMALLER);
		this.cbEquals.setFont(Constants.FONT_SMALLER);
		
		this.labelFrom.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelTo.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelPrice.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		this.labelCategory.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		
		//Add to Panels
		panelTop.add(this.labelFrom);
		panelTop.add(this.dateFrom);
		panelTop.add(this.labelTo);
		panelTop.add(this.dateTo);
		panelCenter.add(this.labelPrice);
		panelCenter.add(this.cbEquals);
		panelCenter.add(this.tfValue);
		panelCenter.add(this.labelCategory);
		panelCenter.add(this.cbCategory);
		this.filters.add(panelTop);
		this.filters.add(panelCenter);
		this.filters.add(Box.createRigidArea(new Dimension(0, 500)));
		
	}

	private void initTable(){
		//Initialization
		this.model = new DefaultTableModel(this.columnNames, 0){
			public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
			}
		};
		
		this.tableTrans = new JTable(this.model);
		updateTable();
		
		//Properties
		this.tableTrans.getTableHeader().setFont(Constants.FONT_SMALLER_BOLD);
		this.tableTrans.setFont(Constants.FONT_SMALLER);
		this.tableTrans.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    this.tableTrans.setFillsViewportHeight(true);
	    this.tableTrans.setRowHeight(30);
	}
	
	private void updateTable() {
		List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions();
		String[] currentData = new String[this.columns];
		
		for(Transaction tr: transactions) {
			currentData[0] = tr.getDateInput().toString();
			currentData[1] = Constants.DATABASE_SERVICE.getCategoryName(tr.getCategoryID());
			currentData[2] = tr.getDesc();
			currentData[3] = Double.toString(tr.getAmount());
			currentData[4] = tr.getLinkReceipt();
			currentData[5] = tr.getDateEdit().toString();
			currentData[6] = "";
			currentData[7] = "";
			this.model.addRow(currentData);
			this.tableTrans.getColumnModel().getColumn(columns - 1).setCellRenderer(new HistoryTableCellRenderer(Constants.ICON_EDIT));
			this.tableTrans.getColumnModel().getColumn(columns - 2).setCellRenderer(new HistoryTableCellRenderer(Constants.ICON_DELETE));
		}
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
		// TODO Auto-generated method stub
		this.tfValue.setText("");
		this.cbCategory.setSelectedIndex(0);
		this.cbEquals.setSelectedIndex(0);
	}

	@Override
	public void backButtonPressed() {
		// TODO Auto-generated method stub
		Globals.activeUser = person;
		Main.changeScreen(new Menu(person, false));
	}

	@Override
	public void refreshButtonPressed() {
		// TODO Auto-generated method stub
		
	}

}
