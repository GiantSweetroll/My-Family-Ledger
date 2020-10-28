package models;

public class Account
{
	//Fields
	private int id;
	private double balance;
	
	//Constructor
	public Account(int id, double balance)
	{
		this.id = id;
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
	 * Gets the ID
	 * @return the ID
	 */
	public int getID()
	{
		return id;
	}
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
	 * Updates the ID.
	 * @param id - Must be greater than zero.
	 */
	public void setID(int id)
	{
		if (id > 0)
		{
			this.id = id;
		}
	}
	/**
	 * Sets the balance with a new value.
	 * @param balance
	 */
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
}
