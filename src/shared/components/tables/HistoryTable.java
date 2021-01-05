package shared.components.tables;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import models.Transaction;

public abstract class HistoryTable extends AbstractLedgerTable implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4203762518720892771L;
	//Fields
	
	//Constructors
	public HistoryTable(List<Transaction> transaction, String[] headers) {
		super();
		this.init(transaction);
		this.headers = headers;
	}
	
	public HistoryTable(String[] headers)
	{
		super();
		this.init(new ArrayList<Transaction>());
		this.headers = headers;
	}
	
	//Private methods
	private void init(List<Transaction> transaction)
	{
		//Initialization
		this.updateData(transaction);
//		this.setTableHeader(null);		//Remove table headers
		this.addMouseListener(this);
	}
	
	//Abstract methods
	protected abstract void onEditPressed(Transaction tr);
	protected abstract void onDeletePressed(int index);
	
	//Overridden Methods
	@Override
	public void mouseClicked(MouseEvent e) 
	{	
		int row = this.rowAtPoint(e.getPoint());
		int col = this.columnAtPoint(e.getPoint());
		
		if (col == this.tableData[0].length-1)		//If last column is pressed
		{
			int selectedIndex = this.convertRowIndexToModel(row);
			this.onEditPressed((Transaction)this.data.get(selectedIndex));
		}
		else if (col == this.tableData[0].length-2)		//If last column is pressed
		{
			int selectedIndex = this.convertRowIndexToModel(row);
			this.onDeletePressed(selectedIndex);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};
	
	//Testing
	/*
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
	}	*/
}
