package models;

public class Person extends DatabaseItem
{
	//Constants
	public static final String FIRST_NAME = "firstName",
								LAST_NAME = "lastName";
	
	//Fields
	private String firstName, lastName;
	
	//Constructor
	public Person(int id, String firstName, String lastName) 
	{
		super(id);
		this.setFullName(firstName, lastName);
	}

	//Getters
	/**
	 * Get the first name of the Person
	 * @return the first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	/**
	 * Get the last name of the Person
	 * @return the last name
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * Gets the full name of the Person
	 * @return the full name
	 */
	public String getFullName()
	{
		return this.firstName + " " + this.lastName;
	}
	
	//Setters
	/**
	 * Set the first name of the Person.
	 * @param firstName - first name cannot be empty.
	 */
	public void setFirstName(String firstName) 
	{
		if (!firstName.equals(""))
		{
			this.firstName = firstName;
		}
	}
	/**
	 * Set the last name of the Person
	 * @param lastName - the last name
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	/**
	 * Updates the first and last name of the Person. If firstName parameter is an empty String, only the last name will be updated.
	 * @param firstName - first name cannot be empty
	 * @param lastName - the last name
	 */
	public void setFullName(String firstName, String lastName)
	{
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
}
