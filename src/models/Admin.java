package models;

public class Admin 
{
	//Fields
	private int id;
	private String firstName, lastName;
	
	//Constructor
	public Admin(int id, String firstName, String lastName) 
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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
	 * Gets the first name
	 * @return the first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	/**
	 * Gets the last name
	 * @return the last name
	 */
	public String getLastName() 
	{
		return lastName;
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
	 * Updates the first name. 
	 * @param firstName - Cannot be empty.
	 */
	public void setFirstName(String firstName)
	{
		if (!firstName.equals(""))
		{
			this.firstName = firstName;
		}
	}
	/**
	 * Updates the last name.
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
