package shared.components.listview;

import java.awt.Color;

import models.Transaction;
import shared.Constants;

public class TransactionTile extends ListTile
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733432221115823308L;
	//Fields
	protected Transaction transaction;
	
	//Constructor
	public TransactionTile(Transaction tr)
	{
		//Initialization
		super();
		this.transaction = tr;
		
		//Properties
		this.updateData();
	}
	
	//Public Methods
	/**
	 * Set the Transaction object to be represented.
	 * @param tr a Transaction object
	 */
	public void setTransaction(Transaction tr)
	{
		this.transaction = tr;
		this.updateData();
	}
	/**
	 * Get the Transaction object represented
	 * @return a Transaction object
	 */
	public Transaction getTransaction()
	{
		return this.transaction;
	}
	
	//Protected methods
	protected void updateData()
	{
		this.setTitle(Constants.DATABASE_SERVICE.getCategoryName(this.transaction.getCategoryID()));		//TODO: use DatabaseService to get Category name
		this.setSubtitle(this.transaction.getDesc());
		this.setTopLeftText(Constants.DATE_FORMAT.format(this.transaction.getDateInput()));
		double amount = this.transaction.getAmount();
		this.setTopRightText("Rp. " + (amount < 0 ? amount*-1d : amount));
		
		this.labTopRight.setForeground(amount < 0 ? Color.RED : Color.BLUE);
	}
}
