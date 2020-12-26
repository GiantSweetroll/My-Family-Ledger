package models;

public class DatabaseItem 
{
	//Fields
	protected Integer id = 0;
	
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
	public Integer getID()
	{
		return this.id;
	}
	
	//Setters
	/**
	 * Updates the ID
	 * @param id - the new ID. Must be greater than zero.
	 */
	public void setID(Integer id)
	{
		if (id > 0)
		{
			this.id = id;
		}
	}
}
