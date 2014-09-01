package database;
/**
 * Constants such as table names and headers.
 */

/**
 * Constants for the database class. Was created by Mike Westbrook, and modified by Cody Shafer.
 * The price for this was a beer.
 * 
 * @author Mike Westbrook
 * @author Cody Shafer
 * @version 2.1
 */
public class DatabaseConstants
{
	/**
	 * Names of the different tables.
	 */
	protected static final String CASEINFO = "CaseInfo";
	
	protected static final String LOCATION = "location";
	
	protected static final String STATUS = "status";

	/**
	 * User name for the database.
	 */
	protected static final String USERNAME = "robot";

	/**
	 * Determines which driver to use. 
	 */
	protected static final String DBMS = "mysql";

	/**
	 * Location of the database. 
	 */
	protected static final String SERVER_NAME = "192.168.1.17";

	/**
	 * Port database is listening on.
	 */
	protected static final String PORT = "3306";

	/**
	 * Name of the database to use.
	 */
	protected static final String DATABASE = "warehouse";
}