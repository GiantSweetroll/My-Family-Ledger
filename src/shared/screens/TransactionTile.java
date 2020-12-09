package shared.screens;

import models.Transaction;
import shared.Constants;

public class TransactionTile extends ListTile
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733432221115823308L;
	//Fields
	private Transaction transaction;
	
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
	
	//Private methods
	private void updateData()
	{
		this.setTitle("");		//TODO: use DatabaseService to get Category name
		this.setSubtitle(this.transaction.getDesc());
		this.setTopLeftText(Constants.DATE_FORMAT.format(this.transaction.getDateInput()));
		this.setTopRightText("Rp. " + this.transaction.getAmount());
	}
}
