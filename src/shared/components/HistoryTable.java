package shared.components;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.ScrollPaneManager;
import models.DatabaseItem;
import models.Transaction;
import shared.Constants;
import shared.Methods;

public class HistoryTable extends AbstractLedgerTable implements MouseListener{
	
//	private final String[] columnNames2 = {"Date", "Category", "Name", "Amount (Rp.)", "Receipt Link", "Last Modified", "Action"};
	private final String[] columnNames = {"Id", "Category Id", "User Id", "DateInput" , "DateEdit" , "Amount (Rp.)", "Desc", "Receipt Link", "Action", "Action"};
	
	
	public HistoryTable(List<Transaction> transaction) {
		super();
		this.init(transaction);
	}
	
	public HistoryTable()
	{
		super();
		this.init(new ArrayList<Transaction>());
	}
	
	private void init(List<Transaction> transaction)
	{
		//Initialization
		this.updateData(transaction);
		this.setTableHeader(null);		//Remove table headers
		this.addMouseListener(this);
		this.setRowHeight(50);
	
	}
	
	@Override
	public void updateData(List<? extends DatabaseItem> transaction) 
	{
		this.updateData(transaction, Methods.convertTransactionToTableRowData((List<Transaction>) transaction), columnNames);
		
		//Apply icon renderer
		if (this.tableData.length > 0)
		{
			this.getColumnModel().getColumn(this.tableData[0].length-1).setCellRenderer(new IconCellRenderer(Constants.ICON_EDIT));
			this.getColumnModel().getColumn(this.tableData[0].length-2).setCellRenderer(new IconCellRenderer(Constants.ICON_DELETE));
		}
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String str = "2015-03-31";
        Date date1 = Date.valueOf(str);
		transactions.add(new Transaction(1, 1, 1, date, date1, 200000, "desc", "gugal.com"));
		transactions.add(new Transaction(2, 1, 1, date1, date, 200000, "desc1", "gugil.com"));
		transactions.add(new Transaction(3, 2, 2, date, date1, 200000, "desc2", "gugul.com"));
		transactions.add(new Transaction(4, 2, 3, date1, date, 200000, "desc3", "gugel.com"));
		transactions.add(new Transaction(5, 2, 4, date, date, 200000, "desc4", "gugol.com"));
		HistoryTable table = new HistoryTable();
		JLabel label = new JLabel(Constants.ICON_DELETE);
		JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(table);
		
		//Properties
		table.updateData(transactions);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Add to frame
		frame.add(scroll);
		frame.add(label);
		
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{	
		int row = this.rowAtPoint(e.getPoint());
		int col = this.columnAtPoint(e.getPoint());
		
		if (col == this.tableData[0].length-1)		//If last column is pressed
		{
			int selectedIndex = this.convertRowIndexToModel(row);
			System.out.println("Future to be deleted Transaction ID: " + this.data.get(selectedIndex).getID());
		}
		else if (col == this.tableData[0].length-2)		//If last column is pressed
		{
			int selectedIndex = this.convertRowIndexToModel(row);
			System.out.println("Future to be deleted lalalalala: " + this.data.get(selectedIndex).getID());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
