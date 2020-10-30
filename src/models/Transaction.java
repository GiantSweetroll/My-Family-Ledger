package models;

import java.sql.Date;

public class Transaction extends DatabaseItem
{
	//Constants
	public static final String ID = "transaction_id",
								DATE_INPUT = "dateInput",
								DATE_EDIT = "dateEdit",
								AMOUNT = "amount",
								CATEGORY_ID = "cat_id",
								DESC = "desc",
								LINK_RECEIPT = "linkReceipt",
								USER_ID = "user_id";
	
	//Fields
	private int categoryID, userID;
	private Date dateInput, dateEdit;
	private double amount;
	private String desc, linkReceipt;
	
	//Constructors
	public Transaction(int id, 
						int categoryID, 
						int userID,
						Date dateInput, 
						Date dateEdit, 
						double amount,
						String desc,
						String linkReceipt) 
	{
		super(id);
		this.init(categoryID, userID, dateInput, dateEdit, amount, desc, linkReceipt);
	}
	public Transaction(int id, 
						int categoryID,
						int userID, 
						Date dateInput,
						Date dateEdit, 
						double amount, 
						String desc)
	{
		super(id);
		this.init(categoryID, userID, dateInput, dateEdit, amount, desc, "");
	}
	public Transaction(int categoryID,
						int userID, 
						Date dateInput,
						Date dateEdit, 
						double amount, 
						String desc)
	{
		super(0);
		this.init(categoryID, userID, dateInput, dateEdit, amount, desc, "");
	}
	public Transaction(int categoryID, 
						int userID,
						Date dateInput, 
						Date dateEdit, 
						double amount,
						String desc,
						String linkReceipt) 
	{
		super(0);
		this.init(categoryID, userID, dateInput, dateEdit, amount, desc, linkReceipt);
	}
	
	
	//Private Methods
	private void init(int categoryID,
						int userID, 
						Date dateInput, 
						Date dateEdit, 
						double amount, 
						String desc,
						String linkReceipt)
	{
		this.setCategoryID(categoryID);
		this.setUserID(userID);
		this.setDateInput(dateInput);
		this.setDateEdit(dateEdit);
		this.setAmount(amount);
		this.setDesc(desc);
		this.setLinkReceipt(linkReceipt);
	}
	
	//Public Methods
	//Getters
	/**
	 * Get the Category ID referenced in this Transaction
	 * @return the referenced Category ID
	 */
	public int getCategoryID()
	{
		return categoryID;
	}
	/**
	 * Get the User ID referenced in this Transaction
	 * @return the referenced User ID
	 */
	public int getUserID()
	{
		return userID;
	}
	/**
	 * Get the Date of input
	 * @return the input date
	 */
	public Date getDateInput() 
	{
		return dateInput;
	}
	/**
	 * Get the Date of last edit
	 * @return the last edit date
	 */
	public Date getDateEdit() 
	{
		return dateEdit;
	}
	/**
	 * Get the amount/value for this Transaction
	 * @return the amount/value
	 */
	public double getAmount()
	{
		return amount;
	}
	/**
	 * Get the description for this Transaction
	 * @return the description
	 */
	public String getDesc() 
	{
		return desc;
	}
	/**
	 * Get the URL string for the receipt image. The URL will refer to Firebase storage.
	 * @return the URL string pointing to Firebase storage.
	 */
	public String getLinkReceipt()
	{
		return linkReceipt;
	}
	
	//Setters
	/**
	 * Change the referenced Category ID
	 * @param categoryID - the new Category ID. Must be greater than zero.
	 */
	public void setCategoryID(int categoryID) 
	{
		if (categoryID > 0)
		{
			this.categoryID = categoryID;
		}
	}
	/**
	 * Change the referenced User ID.
	 * @param userID - the new User ID, must be greater than zero.
	 */
	public void setUserID(int userID)
	{
		if (userID > 0)
		{
			this.userID = userID;
		}
	}
	/**
	 * Change the input date
	 * @param dateInput - the new input date
	 */
	public void setDateInput(Date dateInput) 
	{
		this.dateInput = dateInput;
	}
	/**
	 * Change the date of last edit.
	 * @param dateEdit - the new last edit date
	 */
	public void setDateEdit(Date dateEdit) 
	{
		this.dateEdit = dateEdit;
	}
	/**
	 * Changed the amount/value for this Transaction.
	 * @param amount - the new amount/value of this Transaction. It changes the value, not increment or decrement it.
	 */
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	/**
	 * Change the description of this Transaction.
	 * @param desc - the new description.
	 */
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * Change the URL string for this Transaction.
	 * @param linkReceipt - the URL string. Make sure it is an URL that points to a Firebase storage item.
	 */
	public void setLinkReceipt(String linkReceipt)
	{
		this.linkReceipt = linkReceipt;
	}
}
