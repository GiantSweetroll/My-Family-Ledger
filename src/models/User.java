package models;

public class User extends Person
{
	//Constants
	public static final String ID = "user_id",
								ACCOUNT_ID = "acc_id",
								ADMIN_ID = "admin_id";
	
	//Fields
	private int accountID, adminID;
	
	//Constructor
	public User(int id, int accountID, int adminID, String firstName, String lastName) 
	{
		super(id, firstName, lastName);
		this.setAccountID(accountID);
		this.setAdminID(adminID);
	}
	public User(int accountID, int adminID, String firstName, String lastName) 
	{
		super(firstName, lastName);
		this.setAccountID(accountID);
		this.setAdminID(adminID);
	}
	public User(int accountID, int adminID, String firstName, String lastName, String email, String password)
	{
		super(firstName, lastName, email, password);
		this.setAccountID(accountID);
		this.setAdminID(adminID);
	}
	public User(int id, int accountID, int adminID, String firstName, String lastName, String email, String password)
	{
		super(id, firstName, lastName, email, password);
		this.setAccountID(accountID);
		this.setAdminID(adminID);
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
