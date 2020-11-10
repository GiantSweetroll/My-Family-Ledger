package models;

public class Admin extends Person
{
	//Constants
	public static final String ID = "admin_id";
	
	//Constructor
	public Admin(int id, String firstName, String lastName) 
	{
		super(id, firstName, lastName);
	}
	public Admin(String firstName, String lastName) 
	{
		super(firstName, lastName);
	}
	public Admin(String firstName, String lastName, String email, String password)
	{
		super(firstName, lastName, email, password);
	}
	public Admin(int id, String firstName, String lastName, String email, String password)
	{
		super(id, firstName, lastName, email, password);
	}
}
