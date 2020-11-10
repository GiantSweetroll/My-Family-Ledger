package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import models.Admin;
import models.AdminCategory;
import models.Category;
import models.Person;
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
	/**
	 * Update an Admin record. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the Admin record to be updated
	 * @param admin - an Admin object with the new data
	 */
	public void update(int id, Admin admin)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_ADMINS 
									+ " SET "
										+ Admin.ID + "=" + admin.getID() + ", "
										+ Admin.FIRST_NAME + "=\'" + admin.getFirstName() + "\'," 
										+ Admin.LAST_NAME + "=\'" + admin.getLastName() + "\'" 
									+ " WHERE " + Admin.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Updates a record of a Category. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the Category record to be updated
	 * @param category - a Categroy object containing the new updated data
	 */
	public void update(int id, Category category)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_CATEGORIES 
									+ " SET "
										+ Category.ID + "=" + category.getID() + ", "
										+ Category.NAME + "=\'" + category.getName() + "\'," 
										+ Category.DESC + "=\'" + category.getDesc() + "\'," 
										+ Category.ADMIN_ID + "=" + category.getAdminID()
									+ " WHERE " + Category.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Updates a record of an AdminCategory. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the AdminCategory record to be updated
	 * @param adminCat - an AdminCategory object containing the new updated data
	 */
	public void update(int id, AdminCategory adminCat)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_CATEGORIES 
									+ " SET "
										+ AdminCategory.ID + "=" + adminCat.getID() + ", "
										+ AdminCategory.CATEGORY_ID + "=" + adminCat.getCategoryID() + "," 
										+ AdminCategory.ADMIN_ID + "=" + adminCat.getAdminID() 
									+ " WHERE " + AdminCategory.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Updates a record of an Account. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the Account record to be updated
	 * @param adminCat - an Account object containing the new updated data
	 */
	public void update(int id, Account account)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_ACCOUNT 
									+ " SET "
										+ Account.ID + "=" + account.getID() + ", "
										+ Account.BALANCE + "=" + account.getBalance()
									+ " WHERE " + Account.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Updates a record of an User. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the User record to be updated
	 * @param adminCat - an User object containing the new updated data
	 */
	public void update(int id, User user)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_USERS
									+ " SET "
										+ User.ID + "=" + user.getID() + ", "
										+ User.ACCOUNT_ID + "=" + user.getAccountID() + ","
										+ User.ADMIN_ID + "=" + user.getAdminID() + ","
										+ User.FIRST_NAME + "=\'" + user.getFirstName() + "\'," 
										+ User.LAST_NAME + "=\'" + user.getLastName() + "\'" 
									+ " WHERE " + User.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Updates a record of an Transaction. This process updates all data in the record. It does not check for changes.
	 * @param id - the id of the Transaction record to be updated
	 * @param adminCat - an Transaction object containing the new updated data
	 */
	public void update(int id, Transaction transaction)
	{
		PreparedStatement ps = null;
		try
		{
			ps = this.prepStatement("UPDATE " + TABLE_TRANSACTIONS
									+ " SET "
										+ Transaction.ID + "=" + transaction.getID() + ", "
										+ Transaction.USER_ID + "=" + transaction.getUserID() + ","
										+ Transaction.CATEGORY_ID + "=" + transaction.getCategoryID() + ","
										+ Transaction.AMOUNT + "=" + transaction.getAmount() + ","
										+ Transaction.DATE_INPUT + "=" + transaction.getDateInput() + ","
										+ Transaction.DATE_EDIT + "=" + transaction.getDateEdit() + ","
										+ Transaction.DESC + "=\'" + transaction.getDesc() + "\'," 
										+ Transaction.LINK_RECEIPT + "=\'" + transaction.getLinkReceipt() + "\'" 
									+ " WHERE " + Transaction.ID + "=" + id);
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
				catch(SQLException ex) {}
			}
		}
	}
	/**
	 * Performs a select * operation on the Admins table.
	 * @return a List containing Admin objects
	 */
	public List<Admin> getAllAdmins()
	{
		List<Admin> admins = new ArrayList<Admin>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_ADMINS);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Admin admin = new Admin(rs.getInt(Admin.ID),
										rs.getString(Admin.FIRST_NAME),
										rs.getString(Admin.LAST_NAME),
										rs.getString(Admin.EMAIL),
										rs.getString(Admin.PASSWORD));
				admins.add(admin);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return admins;
	}
	/**
	 * Performs a select * operation on the Categories table.
	 * @return a List containing Category objects
	 */
	public List<Category> getAllCategories()
	{
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_CATEGORIES);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Category cat = new Category(rs.getInt(Category.ID),
											rs.getInt(Category.ADMIN_ID),
											rs.getString(Category.DESC),
											rs.getString(Category.NAME));
				categories.add(cat);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return categories;
	}
	/**
	 * Performs a select * operation on the AdminCategories table.
	 * @return a List containing AdminCategory objects
	 */
	public List<AdminCategory> getAllAdminCategories()
	{
		List<AdminCategory> adminCats = new ArrayList<AdminCategory>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_ADMIN_CATEGORIES);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				AdminCategory adminCat = new AdminCategory(rs.getInt(AdminCategory.ID), 
															rs.getInt(AdminCategory.ADMIN_ID),
															rs.getInt(AdminCategory.CATEGORY_ID));
				adminCats.add(adminCat);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return adminCats;
	}
	/**
	 * Performs a select * operation on the Accounts table.
	 * @return a List containing Account objects
	 */
	public List<Account> getAllAccounts()
	{
		List<Account> accounts = new ArrayList<Account>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_ACCOUNT);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Account acc = new Account(rs.getInt(Account.ID), 
											rs.getDouble(Account.BALANCE));
				accounts.add(acc);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return accounts;
	}
	/**
	 * Performs a select * operation on the Users table.
	 * @return a List containing User objects
	 */
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_USERS);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				User user = new User(rs.getInt(User.ID),
										rs.getInt(User.ACCOUNT_ID),
										rs.getInt(User.ADMIN_ID),
										rs.getString(User.FIRST_NAME),
										rs.getString(User.LAST_NAME),
										rs.getString(User.EMAIL),
										rs.getString(User.PASSWORD));
				users.add(user);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return users;
	}
	/**
	 * Performs a select * operation on the Transactions table.
	 * @return a List containing Transaction objects
	 */
	public List<Transaction> getAllTransactions()
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_TRANSACTIONS);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Transaction tr = new Transaction(rs.getInt(Transaction.ID),
													rs.getInt(Transaction.USER_ID),
													rs.getDate(Transaction.DATE_INPUT),
													rs.getDate(Transaction.DATE_EDIT),
													rs.getDouble(Transaction.AMOUNT),
													rs.getString(Transaction.DESC),
													rs.getString(Transaction.LINK_RECEIPT));
				transactions.add(tr);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return transactions;
	}
	/**
	 * Get all Transactions by the selected user.
	 * @param userID - the user ID used to filter the results.
	 * @return a List containing Transaction objects
	 */
	public List<Transaction> getAllTransactions(int userID)
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + Transaction.ID + " = " + userID);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Transaction tr = new Transaction(rs.getInt(Transaction.ID),
													rs.getInt(Transaction.USER_ID),
													rs.getDate(Transaction.DATE_INPUT),
													rs.getDate(Transaction.DATE_EDIT),
													rs.getDouble(Transaction.AMOUNT),
													rs.getString(Transaction.DESC),
													rs.getString(Transaction.LINK_RECEIPT));
				transactions.add(tr);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return transactions;
	}
	/**
	 * Get all Transactions by the selected user within the selected input date range (inclusive).
	 * @param userID - the user ID used to filter the results.
	 * @param dateMin - the lower bound Date object
	 * @param dateMax - the upper bound Date object
	 * @return a List containing Transaction objects
	 */
	public List<Transaction> getAllTransactions(int userID, Date dateMin, Date dateMax)
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_TRANSACTIONS + " WHERE " + Transaction.ID + " = " + userID
										+ " AND " + Transaction.DATE_INPUT + " >= " + dateMin
										+ " AND " + Transaction.DATE_INPUT + " <= " + dateMax);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Transaction tr = new Transaction(rs.getInt(Transaction.ID),
													rs.getInt(Transaction.USER_ID),
													rs.getDate(Transaction.DATE_INPUT),
													rs.getDate(Transaction.DATE_EDIT),
													rs.getDouble(Transaction.AMOUNT),
													rs.getString(Transaction.DESC),
													rs.getString(Transaction.LINK_RECEIPT));
				transactions.add(tr);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return transactions;
	}
	/**
	 * Get all Transactions within the selected input date range (inclusive).
	 * @param dateMin - the lower bound Date object
	 * @param dateMax - the upper bound Date object
	 * @return a List containing Transaction objects
	 */
	public List<Transaction> getAllTransactions(Date dateMin, Date dateMax)
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			ps = this.prepStatement("SELECT * FROM " + TABLE_TRANSACTIONS 
										+ " WHERE " + Transaction.DATE_INPUT + " >= " + dateMin
										+ " AND " + Transaction.DATE_INPUT + " <= " + dateMax);
			rs = ps.executeQuery();
			
			//Loop through the result set
			while (rs.next())
			{
				Transaction tr = new Transaction(rs.getInt(Transaction.ID),
													rs.getInt(Transaction.USER_ID),
													rs.getDate(Transaction.DATE_INPUT),
													rs.getDate(Transaction.DATE_EDIT),
													rs.getDouble(Transaction.AMOUNT),
													rs.getString(Transaction.DESC),
													rs.getString(Transaction.LINK_RECEIPT));
				transactions.add(tr);
			}
			
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return transactions;
	}
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public Person getLogin(String email, String password)
	{
		Person p = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			//Check in admin first
			ps = this.prepStatement("SELECT * FROM " + TABLE_ADMINS 
									+ " WHERE " + Admin.EMAIL + " = " + email
									+ " AND " + Admin.PASSWORD + " = " + password);
			rs = ps.executeQuery();
			//Check if any account in Admin matches the credentials
			if (rs.next())
			{
				//If yes, return it
				p = new Admin(rs.getInt(Admin.ID),
								rs.getString(Admin.FIRST_NAME),
								rs.getString(Admin.LAST_NAME),
								rs.getString(Admin.EMAIL),
								rs.getString(Admin.PASSWORD));
			}
			else
			{
				rs.close();
				ps.close();
				//If not check in User table
				ps = this.prepStatement("SELECT * FROM " + TABLE_USERS 
										+ " WHERE " + User.EMAIL + " = " + email
										+ " AND " + User.PASSWORD + " = " + password);
				rs = ps.executeQuery();
				
				if (rs.next())
				{
					p = new User(rs.getInt(User.ID),
									rs.getInt(User.ACCOUNT_ID),
									rs.getInt(User.ADMIN_ID),
									rs.getString(User.FIRST_NAME),
									rs.getString(User.LAST_NAME),
									rs.getString(User.EMAIL),
									rs.getString(User.PASSWORD));
				}
			}
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
				catch(SQLException ex) {}
			}
			
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch(SQLException ex) {}
			}
		}
		
		return p;
	}
	
	public static void main (String args[])
	{
		DatabaseService ds = new DatabaseService();
	}
}
