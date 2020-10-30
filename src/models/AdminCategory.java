package models;

public class AdminCategory extends DatabaseItem
{

	//Fields
	private int adminID, categoryID;
	
	//Constructor
	public AdminCategory(int id, int adminID, int categoryID)
	{
		super(id);
		this.setAdminID(adminID);
		this.setCategoryID(categoryID);
	}
	public AdminCategory(int adminID, int categoryID)
	{
		super(0);
		this.setAdminID(adminID);
		this.setCategoryID(categoryID);
	}

	//Getters
	/**
	 * Get the referenced Admin ID
	 * @return the referenced Admin ID
	 */
	public int getAdminID()
	{
		return adminID;
	}
	/**
	 * Get the referenced Category ID
	 * @return the referenced Category ID
	 */
	public int getCategoryID()
	{
		return categoryID;
	}

	//Setters
	/**
	 * Set the referenced Category ID
	 * @param categoryID - Must be greater than zero
	 */
	public void setCategoryID(int categoryID) 
	{
		if (categoryID > 0)
		{
			this.categoryID = categoryID;
		}
	}
	/**
	 * Set the referenced Admin ID
	 * @param adminID - Must be greater than zero
	 */
	public void setAdminID(int adminID)
	{
		if (adminID > 0)
		{
			this.adminID = adminID;
		}
	}
}
