package models;

public class User extends Person
{
	//Fields
	private int accountID, adminID;
	
	//Constructor
	public User(int id, int accountID, int adminID, String firstName, String lastName) 
	{
		super(id, firstName, lastName);
		this.accountID = accountID;
		this.adminID = adminID;
	}
	
	//Getters
	/**
	 * Gets the Account ID referenced by this User
	 * @return the referenced Account ID
	 */
	public int getAccountID() 
	{
		return accountID;
	}
	/**
	 * Gets the Admin ID referenced by this User
	 * @return the referenced Admin ID
	 */
	public int getAdminID() 
	{
		return adminID;
	}

	//Setters
	/**
	 * Change the Account ID referenced to this User
	 * @param accountID - the referenced Account ID, must be greater than zero.
	 */
	public void setAccountID(int accountID)
	{
		if (accountID > 0)
		{
			this.accountID = accountID;
		}
	}
	/**
	 * Change the Admin ID referenced to this User
	 * @param adminID - the referenced Admin ID, must be greater than zero.
	 */
	public void setAdminID(int adminID) 
	{
		if (adminID > 0)
		{
			this.adminID = adminID;
		}
	}
}
