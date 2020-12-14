package screens.history;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import models.Person;
import shared.Constants;
import shared.Methods;
import shared.screens.HistoryPanel;

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
	
	String[][] data = {
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "none", "none"},
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "none", "none"},
			{"01/01/01", "Food", "Gardyan", "1000000", "google.com", "none", "none"}
	};
	String[] columnNames = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified", "Action"};
	
	//Constructor
	public TransactionHistory(Person person) {
		super(person);
		
		//Initialization
		this.person = person;
		this.initPanelFilters();
		this.initTable();
		
		//Properties
		this.setFilterPanel(this.filters);
		this.setTable(tableTrans);
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
		this.tfValue = new JTextField("Enter Value", 10);
		
		//make combo box for equality signs
		this.cbEquals = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);
		cbEquals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo box.
                JComboBox cbEquals = (JComboBox) event.getSource();
                // Print the selected items and the action command.
                Object selected = cbEquals.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
            }
		});
		
		//make combo box for category
		String categories[] = { "Select Item", "Food", "Transport", "Household" };
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
		this.filters.setLayout(boxlayout);
		this.filters.setBackground(Color.WHITE);
		this.labelFrom.setFont(Constants.FONT_SMALLER);
		this.labelTo.setFont(Constants.FONT_SMALLER);
		this.labelPrice.setFont(Constants.FONT_SMALLER);
		this.labelCategory.setFont(Constants.FONT_SMALLER);
		this.tfValue.setFont(Constants.FONT_SMALLER);
		this.tfValue.setForeground(Constants.COLOR_TEXT_GRAY);
		this.cbCategory.setFont(Constants.FONT_SMALLER);
		this.cbEquals.setFont(Constants.FONT_SMALLER);
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		
		//Add to Panels
		panelTop.add(this.labelFrom);
		panelTop.add(this.labelTo);
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
		TableModel model = new DefaultTableModel(this.data, this.columnNames){
			public boolean isCellEditable(int row, int column){
				return false;//This causes all cells to be not editable
			}
		};
		
		this.tableTrans = new JTable(model);
		
		//Properties
		this.tableTrans.getTableHeader().setFont(Constants.FONT_SMALLER_BOLD);
		this.tableTrans.setFont(Constants.FONT_SMALLER);
		this.tableTrans.setPreferredScrollableViewportSize(new Dimension(500, 220));
	    this.tableTrans.setFillsViewportHeight(true);
	    this.tableTrans.setRowHeight(20);
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
		
	}

	@Override
	public void refreshButtonPressed() {
		// TODO Auto-generated method stub
		
	}

}
