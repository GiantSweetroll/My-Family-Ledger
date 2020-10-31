package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Account;
import models.Admin;
import models.AdminCategory;
import models.Category;
import models.Transaction;
import models.User;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://ourworks.systems/",
								USERNAME = "GAR296",
								PASSWORD = "YvQzWA";
	public static final String DB_NAME = "GAR296",
								TABLE_ACCOUNT = "MFL_Account",
								TABLE_ADMINS = "MFL_Admins",
								TABLE_USERS = "MFL_Users",
								TABLE_TRANSACTIONS = "MFL_Transactions",
								TABLE_CATEGORIES = "MFL_Categories",
								TABLE_ADMIN_CATEGORIES = "MFL_AdminCategories";
	
	private Connection c;
	
	//Constructor
	public DatabaseService()
	{
		this.init();
	}
	
	//Private Methods
	/**
	 * Initializes the class. It checks for existance of database. If not create it. Tables are also checked and created if needed.
	 */
	private void init()
	{
		//Connect to mysql database
		try
		{
			//Try to connect to the database
			this.c = DriverManager.getConnection(HOST + DB_NAME, USERNAME, PASSWORD);
			System.out.println("Database connection successful");
			
			//Check for tables
			this.createTables();
		}
		catch (SQLException ex)
		{
			//If it goes here, most likely the database has not been created yet.
			System.err.println(ex.getMessage());
			try
			{
				//Create the database and try to connect again.
				c = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				this.createDatabase();
				this.init();
			} 
			catch (SQLException e) 
			{
				System.err.println(ex.getMessage());
				System.out.println("Database connection failed after creating table");
			}
		}
	}
	/**
	 * Creates the MySQL database.
	 * @throws SQLException
	 */
	private void createDatabase() throws SQLException
	{
		PreparedStatement prep = this.prepStatement("CREATE DATABASE " + DB_NAME);
		prep.execute();
		prep.close();
	}
	/**
	 * Creates the tables if they do not exist yet
	 */
	private void createTables()
	{
		PreparedStatement prep = null;
		
		try
		{
			//Admins table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_ADMINS + "("
										+ Admin.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ Admin.FIRST_NAME + " varchar(255) NOT NULL,"
										+ Admin.LAST_NAME + " varchar(255),"
										+ "primary key (" + Admin.ID + "))");
			prep.execute();
			prep.close();
			
			//Categories table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_CATEGORIES + "("
										+ Category.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ Category.NAME + " varchar(255) NOT NULL,"
										+ Category.DESC + " mediumtext,"
										+ Category.ADMIN_ID + " int unsigned,"
										+ "primary key (" + Category.ID + "),"
										+ "foreign key (" + Category.ADMIN_ID + ") references " + TABLE_ADMINS + "(" + Admin.ID + ") ON UPDATE CASCADE)");
			prep.execute();
			prep.close();
			
			//AdminCategories table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_ADMIN_CATEGORIES + "("
										+ AdminCategory.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ AdminCategory.ADMIN_ID + " int unsigned not null,"
										+ AdminCategory.CATEGORY_ID + " int unsigned not null,"
										+ "primary key (" + AdminCategory.ID + "),"
										+ "foreign key (" + AdminCategory.ADMIN_ID + ") references " + TABLE_ADMINS + "(" + Admin.ID + ") ON UPDATE CASCADE,"
										+ "foreign key (" + AdminCategory.CATEGORY_ID + ") references " + TABLE_CATEGORIES + "(" + Category.ID + ") ON UPDATE CASCADE)");
			prep.execute();
			prep.close();
			
			//Accounts table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_ACCOUNT + "("
										+ Account.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ Account.BALANCE + " decimal(65, 2) NOT NULL,"
										+ "primary key (" + Account.ID + "))");
			prep.execute();
			prep.close();
			
			//Users table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_USERS + "("
										+ User.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ User.FIRST_NAME + " varchar(255) NOT NULL,"
										+ User.LAST_NAME + " varchar(255),"
										+ User.ACCOUNT_ID + " int unsigned NOT NULL,"
										+ User.ADMIN_ID + " int unsigned NOT NULL,"
										+ "primary key (" + User.ID + "),"
										+ "foreign key (" + User.ADMIN_ID + ") references " + TABLE_ADMINS + "(" + Admin.ID + ") ON UPDATE CASCADE,"
										+ "foreign key (" + User.ACCOUNT_ID + ") references " + TABLE_ACCOUNT + "(" + Account.ID + ") ON UPDATE CASCADE)");
			prep.execute();
			prep.close();
			
			//Transactions table
			prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_TRANSACTIONS + "("
										+ Transaction.ID + " int unsigned NOT NULL AUTO_INCREMENT,"
										+ Transaction.DATE_INPUT + " Date NOT NULL,"
										+ Transaction.DATE_EDIT + " Date NOT NULL,"
										+ Transaction.AMOUNT + " decimal(65, 2) NOT NULL,"
										+ Transaction.CATEGORY_ID + " int unsigned NOT NULL,"
										+ Transaction.DESC + " mediumtext NOT NULL,"
										+ Transaction.LINK_RECEIPT + " text(65535),"
										+ Transaction.USER_ID + " int unsigned NOT NULL,"
										+ "primary key (" + Transaction.ID + "),"
										+ "foreign key (" + Transaction.CATEGORY_ID + ") references " + TABLE_CATEGORIES + "(" + Category.ID + ") ON UPDATE CASCADE,"
										+ "foreign key (" + Transaction.USER_ID + ") references " + TABLE_USERS + "(" + User.ID + ") ON UPDATE CASCADE)");
			prep.execute();
			prep.close();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (prep != null)
			{
				try
				{
					prep.close();
				}
				catch(SQLException ex) {}
			}
		}
		
	}
	
	//Public Methods
	/**
	 * Gets the database Connection object
	 * @return the connection to the MySQL
	 */
	public Connection getConnection()
	{
		return this.c;
	}
	/**
	 * Prepares the MySQL Query statement before execution.
	 * @param query - the MySQL query command
	 * @return a PreparedStatement object
	 */
	public PreparedStatement prepStatement(String query)
	{
		try
		{
			PreparedStatement prepStat = c.prepareStatement(query);
			return prepStat;
		}
		catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
			return null;
		}
	}
	/**
	 * Insert an Admin to the Admins table
	 * @param admin - an instance of an Admin object
	 */
	public void insert(Admin admin)
	{
		PreparedStatement ps = null;
		try
		{		
			if (admin.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ADMINS + "("
										+ Admin.FIRST_NAME + ", " + Admin.LAST_NAME +") values ("
										+ "\'" + admin.getFirstName() + "\',"
										+ "\'" + admin.getLastName() + "\'"
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ADMINS + " values (" 
										+ admin.getID() + ", "
										+ "\'" + admin.getFirstName() + "\',"
										+ "\'" + admin.getLastName() + "\'"
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert a Category into the Categories table.
	 * @param category - a Category object
	 */
	public void insert(Category category)
	{
		PreparedStatement ps = null;
		try
		{		
			if (category.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_CATEGORIES + "("
										+ Category.DESC + ", " + Category.NAME + ", " + Category.ADMIN_ID +") values ("
										+ "\'" + category.getDesc() + "\',"
										+ "\'" + category.getName() + "\',"
										+ category.getAdminID()
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_CATEGORIES + " values (" 
										+ category.getID() + ", "
										+ "\'" + category.getDesc() + "\',"
										+ "\'" + category.getName() + "\',"
										+ category.getAdminID()
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert an AdminCategory to the AdminCategories table
	 * @param adminCategory - an AdminCategory object
	 */
	public void insert(AdminCategory adminCategory)
	{
		PreparedStatement ps = null;
		try
		{		
			if (adminCategory.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ADMIN_CATEGORIES + "("
										+ AdminCategory.ADMIN_ID + ", " + AdminCategory.CATEGORY_ID +") values ("
										+ adminCategory.getAdminID() + ","
										+ adminCategory.getCategoryID()
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ADMIN_CATEGORIES + " values (" 
										+ adminCategory.getID() + ", "
										+ adminCategory.getAdminID() + ","
										+ adminCategory.getCategoryID()
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert an Account in the Accounts table
	 * @param account - an Account object
	 */
	public void insert(Account account)
	{
		PreparedStatement ps = null;
		try
		{		
			if (account.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ACCOUNT + "("
										+ Account.BALANCE +") values ("
										+ account.getBalance()
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_ACCOUNT + " values (" 
										+ account.getID() + ", "
										+ account.getBalance()
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert a User to the Users table
	 * @param user - a User object
	 */
	public void insert(User user)
	{
		PreparedStatement ps = null;
		try
		{		
			if (user.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_USERS + "("
										+ User.FIRST_NAME + ", " 
										+ User.LAST_NAME + ", "
										+ User.ACCOUNT_ID + ", " 
										+ User.ADMIN_ID + ") values ("
										+ "\'" + user.getFirstName() + "\',"
										+ "\'" + user.getLastName() + "\',"
										+ user.getAccountID() + ","
										+ user.getAdminID()
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_USERS + " values (" 
										+ user.getID() + ", "
										+ "\'" + user.getFirstName() + "\',"
										+ "\'" + user.getLastName() + "\',"
										+ user.getAccountID() + ","
										+ user.getAdminID()
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	/**
	 * Insert a Transaction into the Transaction table
	 * @param transaction - a Transaction object
	 */
	public void insert(Transaction transaction)
	{
		PreparedStatement ps = null;
		try
		{		
			if (transaction.getID() <= 0)	//If no ID yet
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_TRANSACTIONS + "("
										+ Transaction.DATE_INPUT + ", " 
										+ Transaction.DATE_EDIT + ", "
										+ Transaction.AMOUNT + ", " 
										+ Transaction.CATEGORY_ID + ", " 
										+ Transaction.DESC + ", " 
										+ Transaction.LINK_RECEIPT + ", " 
										+ Transaction.USER_ID + ") values ("
										+ transaction.getDateInput() + ","
										+ transaction.getDateEdit() + ","
										+ transaction.getAmount() + ","
										+ transaction.getCategoryID() + ","
										+ "\'" + transaction.getDesc() + "\',"
										+ "\'" + transaction.getLinkReceipt() + "\',"
										+ transaction.getUserID()
										+ ")");
			}
			else
			{
				ps = this.prepStatement("INSERT INTO " + TABLE_TRANSACTIONS + " values (" 
										+ transaction.getID() + ", "
										+ transaction.getDateInput() + ","
										+ transaction.getDateEdit() + ","
										+ transaction.getAmount() + ","
										+ transaction.getCategoryID() + ","
										+ "\'" + transaction.getDesc() + "\',"
										+ "\'" + transaction.getLinkReceipt() + "\',"
										+ transaction.getUserID()
										+ ")");
			}
			
			ps.execute();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		finally
		{
			if (ps != null)
			{
				try 
				{
					ps.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public static void main (String args[])
	{
		DatabaseService ds = new DatabaseService();
	}
}
