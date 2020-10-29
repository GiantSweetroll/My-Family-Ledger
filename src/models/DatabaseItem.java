package models;

public class DatabaseItem 
{
	//Fields
	protected int id;
	
	//Constructor
	public DatabaseItem(int id)
	{
		this.setID(id);
	}
	
	//Getters
	/**
	 * Get the ID
	 * @return the ID
	 */
	public int getID()
	{
		return this.id;
	}
	
	//Setters
	/**
	 * Updates the ID
	 * @param id - the new ID
	 */
	public void setID(int id)
	{
		if (id > 0)
		{
			this.id = id;
		}
	}
}
