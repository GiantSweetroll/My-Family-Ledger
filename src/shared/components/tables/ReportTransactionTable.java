package shared.components.tables;

import java.util.ArrayList;
import java.util.List;

import models.DatabaseItem;
import models.Transaction;
import shared.Constants;
import shared.components.AmountCellRenderer;

public class ReportTransactionTable extends AbstractLedgerTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -933618050792108080L;
	//Constants
	private static final String[] HEADERS = {"Date", 
												"Category", 
												"Amount (Rp.)", 
												"Notes",
												"Receipt Link", 
												"Last Modified"};
	
	//Constructor
	public ReportTransactionTable(List<Transaction> data)
	{
		super();
		this.updateData(data);
		this.headers = HEADERS;
	}
	public ReportTransactionTable()
	{
		super();
		this.updateData(new ArrayList<Transaction>());
		this.headers = HEADERS;
	}
	
	//Overridden Methods
	@Override
	public void updateData(List<? extends DatabaseItem> data) 
	{
		this.updateData(data, this.convertToTableRowData((List<Transaction>) data), HEADERS);
		
		//Apply icon renderer
		if (this.tableData.length > 0)
		{
			this.getColumnModel().getColumn(2).setCellRenderer(new AmountCellRenderer());
		}
	}
	@Override
	protected String[][] convertToTableRowData(List<? extends DatabaseItem> data) {
		
		String [][] transactions = new String [data.size()][HEADERS.length];
		for (int i = 0 ; i< data.size() ; i++)
		{
			Transaction trans = (Transaction)data.get(i);
			int j = 0;
			transactions[i][j] = String.valueOf(trans.getDateInput());
			transactions[i][++j] = Constants.DATABASE_SERVICE.getCategoryName(trans.getCategoryID());
			transactions[i][++j] = Double.toString(trans.getAmount());
			transactions[i][++j] = trans.getDesc();
			transactions[i][++j] = trans.getLinkReceipt();
			transactions[i][++j] = String.valueOf(trans.getDateEdit());
		}
		
		return transactions;
	}
	@Override
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};
}
