package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService 
{
	private static final String HOST = "jdbc:mysql://ourworks.systems/",
								USERNAME = "GAR296",
								PASSWORD = "YzQvWA";
	public static final String DB_NAME = "MFL",
								TABLE_ACCOUNT = "Account",
								TABLE_ADMINS = "Admins",
								TABLE_USERS = "Users",
								TABLE_TRANSACTIONS = "Transactions",
								TABLE_CATEGORIES = "Categories",
								TABLE_ADMIN_CATEGORIES = "AdminCategories";
	
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
	 * @throws SQLException
	 */
	private void createTables() throws SQLException
	{
		//Movie table
		PreparedStatement prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_MOVIE + "("
													+ "movieID int unsigned NOT NULL AUTO_INCREMENT,"
													+ "movieName varchar(255) NOT NULL,"
													+ "primary key (movieID))");
		prep.execute();
		
		//User table
		prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_USER + "("
									+ "userID int unsigned not null AUTO_INCREMENT,"
									+ "firstName varchar(255) not null,\r\n" + 
										"lastName varchar(255),\r\n" + 
										"username varchar(255) not null,\r\n" + 
										"password varchar(255) not null,"
									+ "primary key (userID))");
		prep.execute();
		
		//Review table
		prep = this.prepStatement("CREATE TABLE if not exists " + TABLE_REVIEW + "("
				+ "reviewID int unsigned not null AUTO_INCREMENT,\r\n" + 
				"movieID int unsigned not null,\r\n" + 
				"userID int unsigned not null,\r\n" + 
				"description mediumtext,\r\n" + 
				"ratingUser decimal(10, 1) not null,\r\n" + 
				"primary key (reviewID),\r\n" + 
				"foreign key (movieID) references Movie(movieID) on update cascade,\r\n" + 
				"foreign key (userID) references User(userID) on update cascade)");
		
		prep.execute();
		prep.close();
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
}
