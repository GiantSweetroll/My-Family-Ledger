package models;

public class Account extends DatabaseItem
{
	//Constants
	public static final String ID = "acc_id",
								BALANCE = "balance";
	
	//Fields
	private double balance;
	
	//Constructor
	public Account(int id, double balance)
	{
		super(id);
		this.balance = balance;
	}
	public Account(double balance)
	{
		super(0);
		this.balance = balance;
	}
	
	//Public Methods
	/**
	 * Updates the total balance with the added value.
	 * @param addedValue
	 */
	public void updateBalance(double addedValue)
	{
		this.balance += addedValue;
	}
	
	//Getters
	/**
	 * Gets the balance
	 * @return the balance
	 */
	public double getBalance() 
	{
		return balance;
	}

	//Setters
	/**
	 * Sets the balance with a new value.
	 * @param balance
	 */
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
}
