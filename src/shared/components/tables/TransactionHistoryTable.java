package shared.components.tables;

import java.util.ArrayList;
import java.util.List;

import models.DatabaseItem;
import models.Transaction;
import shared.Constants;
import shared.components.IconCellRenderer;

public class TransactionHistoryTable extends HistoryTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027209512238544080L;
	//Fields
	private static final String[] HEADERS = {"Date",
											"Category",
											"Description",
											"Amount (Rp)", 
											"Receipt Link",
											"Last Modified",
											"", ""};	//For the actions
	
	//Constructors
	public TransactionHistoryTable(List<Transaction> transaction)
	{
		super(transaction, HEADERS);
	}
	
	public TransactionHistoryTable(String[] headers)
	{
		super(new ArrayList<Transaction>(), HEADERS);
	}
	
	//Overridden Methods
	@Override
	public void updateData(List<? extends DatabaseItem> transaction) 
	{
		this.updateData(transaction, this.convertToTableRowData((List<Transaction>) transaction), this.headers);
		
		//Apply icon renderer (only if it is not a Transfer)
		for (int i=0; i<this.tableData.length; i++)
		{
			if (!this.tableData[i][1].equalsIgnoreCase("Transfer"))
			{
				this.getColumnModel().getColumn(this.tableData[i].length-1).setCellRenderer(new IconCellRenderer(Constants.ICON_EDIT));
				this.getColumnModel().getColumn(this.tableData[i].length-2).setCellRenderer(new IconCellRenderer(Constants.ICON_DELETE));
			}
		}
	}
	
	@Override
	protected void onEditPressed(Transaction tr) 
	{
//		Main.pushScreen(new TransferFunds(Globals.activeUser, tr));
	}

	@Override
	protected void onDeletePressed(int index) 
	{
		Transaction tr = (Transaction)this.data.get(index);
		// TODO delete transaction
		// TODO restore balance
	}

	@Override
	protected String[][] convertToTableRowData(List<? extends DatabaseItem> data)
	{
		String[][] arr = new String[data.size()][10];
		
		for (int i=0; i<data.size(); i++)
		{
			Transaction tr = (Transaction)data.get(i);
			int j = 0;
			arr[i][j] = Constants.DATE_FORMAT.format(tr.getDateInput());
			arr[i][++j] = Constants.DATABASE_SERVICE.getCategoryName(tr.getCategoryID());
			arr[i][++j] = tr.getDesc();
			arr[i][++j] = Double.toString(tr.getAmount());
			arr[i][++j] = tr.getLinkReceipt();
			arr[i][++j] = Constants.DATE_FORMAT.format(tr.getDateEdit());
			arr[i][++j] = "";
			arr[i][++j] = "";
		}
		
		return arr;
	}
}
