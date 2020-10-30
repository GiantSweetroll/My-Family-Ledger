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
}
