package models;

public class Category extends DatabaseItem
{
	//Constants
	public static final String ID = "cat_id",
								DESC = "desc",
								NAME = "cat_name",
								ADMIN_ID = "admin_id";
	
	//Fields
	private int adminID;
	private String desc, name;
	
	//Constructor
	public Category(int id, int adminID, String desc, String name)
	{
		super(id);
		this.adminID = adminID;
		this.desc = desc;
		this.name = name;
	}

	//Getters
	/**
	 * Gets the Admin ID referenced for this Category
	 * @return the Admin ID
	 */
	public int getAdminID() 
	{
		return adminID;
	}
	/**
	 * Get the description details for this Category
	 * @return the description
	 */
	public String getDesc() 
	{
		return desc;
	}
	/**
	 * Get the name of this Category
	 * @return the name of the category
	 */
	public String getName()
	{
		return name;
	}

	//Setters
	/**
	 * Changes the admin ID of this Category
	 * @param adminID - Must be greater than zero
	 */
	public void setAdminID(int adminID)
	{
		if (adminID > 0)
		{
			this.adminID = adminID;
		}
	}
	/**
	 * Change the description details of this Category
	 * @param desc - the new description for the category
	 */
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	/**
	 * Change the name of this Category
	 * @param name - new name for the category
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
}
