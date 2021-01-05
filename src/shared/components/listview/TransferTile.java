package shared.components.listview;

import models.Transaction;
import models.User;
import shared.Constants;

public class TransferTile extends TransactionTile
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7758174477037992453L;

	//Constructor
	public TransferTile(Transaction tr)
	{
		//Initialization
		super(tr);
		this.updateDate();
	}
	
	//Overridden Methods
	protected void updateDate()
	{
		super.updateData();
		
		User user = Constants.DATABASE_SERVICE.getUser(this.transaction.getUserID());
		this.setTitle(user.getFullName());
		this.setSubtitle(user.getEmail());
		this.setBottomLeftText(this.transaction.getDesc());
	}
}
