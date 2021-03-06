package shared.components.tables;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import main.Main;
import models.Account;
import models.DatabaseItem;
import models.Transaction;
import screens.input.InputTransactions;
import shared.Constants;
import shared.Globals;
import shared.components.AmountCellRenderer;
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
											"Last Modified",
											"", ""};	//For the actions
	
	//Constructors
	public TransactionHistoryTable(List<Transaction> transaction)
	{
		super(transaction, HEADERS);
	}
	
	public TransactionHistoryTable()
	{
		super(new ArrayList<Transaction>(), HEADERS);
	}
	
	//Overridden Methods
	@Override
	public void updateData(List<? extends DatabaseItem> transaction) 
	{
		this.updateData(transaction, this.convertToTableRowData((List<Transaction>) transaction), HEADERS);
		
		//Apply icon renderer (only if it is not a Transfer)
		if (this.tableData.length > 0)
		{
			this.getColumnModel().getColumn(3).setCellRenderer(new AmountCellRenderer());
			this.getColumnModel().getColumn(this.tableData[0].length-1).setCellRenderer(new IconCellRenderer(Constants.ICON_EDIT));
			this.getColumnModel().getColumn(this.tableData[0].length-2).setCellRenderer(new IconCellRenderer(Constants.ICON_DELETE));
		}
	}
	
	@Override
	protected void onEditPressed(Transaction tr) 
	{
		Main.pushScreen(new InputTransactions(Globals.activeUser, tr), Constants.FRAME_INPUT_TRANSACTION);
	}

	@Override
	protected void onDeletePressed(int index) 
	{
		Transaction tr = (Transaction)this.data.get(index);
		int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this transaction?", "Delete", JOptionPane.YES_NO_OPTION);
		if (option == 0) {
			Constants.DATABASE_SERVICE.deleteTransaction(tr.getID());
			this.data.remove(index);
			this.updateData(this.data);
			Account curAcc = Constants.DATABASE_SERVICE.getAccount(tr.getUserID());
			curAcc.updateBalance(tr.getAmount() * -1d);
			Constants.DATABASE_SERVICE.update(curAcc.getID(), curAcc);
		}
	}

	@Override
	protected String[][] convertToTableRowData(List<? extends DatabaseItem> data)
	{
		String[][] arr = new String[data.size()][HEADERS.length];
		
		for (int i=0; i<data.size(); i++)
		{
			Transaction tr = (Transaction)data.get(i);
			int j = 0;
			int catID = tr.getCategoryID();
			arr[i][j] = Constants.DATE_FORMAT.format(tr.getDateInput());
			arr[i][++j] = Constants.DATABASE_SERVICE.getCategoryName(catID);
			arr[i][++j] = tr.getDesc();
			arr[i][++j] = Double.toString(tr.getAmount());
			arr[i][++j] = Constants.DATE_FORMAT.format(tr.getDateEdit());
			arr[i][++j] = catID == 1? null : "";
			arr[i][++j] = catID == 1? null : "";
		}
		
		return arr;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{	
		int row = this.rowAtPoint(e.getPoint());
		int col = this.columnAtPoint(e.getPoint());
		int selectedIndex = this.convertRowIndexToModel(row);
		boolean catIsTF = this.tableData[selectedIndex][1].equalsIgnoreCase("Transfer");
		
		if (!catIsTF)	//If transaction is not a transfer, allow press
		{
			if (col == this.tableData[0].length-1)		//If last column is pressed
			{
				this.onEditPressed((Transaction)this.data.get(selectedIndex));
			}
			else if (col == this.tableData[0].length-2)		//If second last column is pressed
			{
				this.onDeletePressed(selectedIndex);
			}
		}
	}
}
