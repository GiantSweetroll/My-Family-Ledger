package models;

public class Person extends DatabaseItem
{
	//Constants
	public static final String FIRST_NAME = "firstName",
								LAST_NAME = "lastName",
								EMAIL = "email",
								PASSWORD = "password";
	
	//Fields
	private String firstName, lastName, email, password;
	
	//Constructor
	public Person(int id, String firstName, String lastName) 
	{
		super(id);
		this.setFullName(firstName, lastName);
		this.setEmail("");
		this.setPassword("");
	}
	public Person(String firstName, String lastName) 
	{
		super(0);
		this.setFullName(firstName, lastName);
		this.setEmail("");
		this.setPassword("");
	}
	public Person(String firstName, String lastName, String email, String password)
	{
		super(0);
		this.setFullName(firstName, lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	public Person(int id, String firstName, String lastName, String email, String password)
	{
		super(id);
		this.setFullName(firstName, lastName);
		this.setEmail(email);
		this.setPassword(password);
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
	/**
	 * Get the Person's email.
	 * @return a String of the email address.
	 */
	public String getEmail()
	{
		return this.email;
	}
	/**
	 * Get the password associated with the Person
	 * @return a String of the password.
	 */
	public String getPassword()
	{
		return this.password;
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
	/**
	 * Set the email of the Person
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	/**
	 * Set the password of the Person
	 * @param password
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
