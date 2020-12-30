package shared.components.tables;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import models.DatabaseItem;
import models.Transaction;
import models.User;
import screens.input.TransferFunds;
import shared.Constants;
import shared.Globals;
import shared.components.AmountCellRenderer;
import shared.components.IconCellRenderer;

public class TransferHistoryTable extends HistoryTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5443332440387293655L;
	//Fields
	private static final String[] HEADERS = {"Date", 
											"Amount (Rp)", 
											"Notes", 
											"Receiver", 
											"Email", 
											"Last Modified",
											"", ""};	//For the actions
	
	//Constructors
	public TransferHistoryTable(List<Transaction> transaction)
	{
		super(transaction, HEADERS);
	}
	
	public TransferHistoryTable(String[] headers)
	{
		super(new ArrayList<Transaction>(), HEADERS);
	}
	
	//Overridden Methods
	@Override
	public void updateData(List<? extends DatabaseItem> transaction) 
	{
		this.updateData(transaction, this.convertToTableRowData((List<Transaction>) transaction), HEADERS);
		
		//Apply icon renderer
		if (this.tableData.length > 0)
		{
			this.getColumnModel().getColumn(1).setCellRenderer(new AmountCellRenderer());
			this.getColumnModel().getColumn(this.tableData[0].length-1).setCellRenderer(new IconCellRenderer(Constants.ICON_EDIT));
			this.getColumnModel().getColumn(this.tableData[0].length-2).setCellRenderer(new IconCellRenderer(Constants.ICON_DELETE));
		}
	}
	
	@Override
	protected void onEditPressed(Transaction tr) 
	{
		Main.pushScreen(new TransferFunds(Globals.activeUser, tr));
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
		String[][] arr = new String[data.size()][8];
		
		for (int i=0; i<data.size(); i++)
		{
			Transaction tr = (Transaction)data.get(i);
			User user = Constants.DATABASE_SERVICE.getUser(tr.getUserID());
			int j = 0;
			arr[i][j] = Constants.DATE_FORMAT.format(tr.getDateInput());
			arr[i][++j] = Double.toString(tr.getAmount());
			arr[i][++j] = tr.getDesc();
			arr[i][++j] = user.getFullName();
			arr[i][++j] = user.getEmail();
			arr[i][++j] = Constants.DATE_FORMAT.format(tr.getDateEdit());
			arr[i][++j] = "";
			arr[i][++j] = "";
		}
		
		return arr;
	}
}
