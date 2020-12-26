package models;

public class Category extends DatabaseItem
{
	//Constants
	public static final String ID = "cat_id",
								DESC = "description",
								NAME = "cat_name",
								ADMIN_ID = "admin_id";
	
	//Fields
	private Integer adminID;
	private String desc, name;
	
	//Constructor
	public Category(int id, Integer adminID, String name, String desc)
	{
		super(id);
		this.setAdminID(adminID);
		this.setDesc(desc);
		this.setName(name);
	}
	public Category(Integer adminID, String name, String desc)
	{
		super(0);
		this.setAdminID(adminID);
		this.setDesc(desc);
		this.setName(name);
	}

	//Getters
	/**
	 * Gets the Admin ID referenced for this Category
	 * @return the Admin ID
	 */
	public Integer getAdminID() 
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
	public void setAdminID(Integer adminID)
	{
		if (adminID != null && adminID > 0)
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
