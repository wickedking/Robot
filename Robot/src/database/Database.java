package database;
/**
 * Interface between client side and data side. 
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

import constants.DBC;
import server.Case;
import server.Location;
import server.Units;


/**
 * Provides a set of methods for the client side to retrieve information out of the database.
 * 
 * Note: Class was modified and change by Cody Shafer. Original author was Mike Westbrook.
 * Was used with permission. The price was a beer. 
 * 
 * @author Mike Westbrook
 * @author Cody Shafer
 * @version 2.2
 */
public class Database{

	/**
	 * The only instance of this database.
	 */
	private static Database me;

	/**
	 * Actual connection to the database.
	 */
	private final Connection my_conn;

	private static final Logger log = Logger.getLogger(Database.class.getName());

	/**
	 * Cannot be directly instantiated.
	 */
	protected Database(){
		try {
			FileHandler fh = new FileHandler("db_log.log", false);
			fh.setFormatter(new SimpleFormatter());
			log.addHandler(fh);
			log.setLevel(Level.FINE);
		} catch (SecurityException | IOException e) {
			e.printStackTrace(); //Can't log cause logger blew up. 
		}
		log.log(Level.INFO, "Started Database");
		my_conn = getConnection();
	}

	/**
	 * @return The only instance of this class.
	 */
	public static Database getInstance(){
		if (me == null){
			me = new Database();
		}
		return me;
	}

	/**
	 * Creates a connection to the database.
	 * @return The connection.
	 */
	private static Connection getConnection() {
		log.log(Level.INFO, "Getting Connection");
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", DatabaseConstants.USERNAME);
		if (DatabaseConstants.DBMS.equals("mysql")) {
			try {
				conn = DriverManager.getConnection(
						"jdbc:" + DatabaseConstants.DBMS + "://" +
								DatabaseConstants.SERVER_NAME +
								":" + DatabaseConstants.PORT + "/" + DatabaseConstants.DATABASE,
								connectionProps);
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		} 
		log.log(Level.INFO, "Connection Recieved");
		return conn;
	}


	/**
	 * Get a list of cases that are currently in pull status.
	 * @return The list of cases to be pulled.
	 */
	public List<Case> getPullCases(){
		//TODO handle case with empty result set. Current implementation does not apparently
		log.log(Level.INFO, "Calling getPullCases");
		PreparedStatement prestate;
		List<Case> caseList = new ArrayList<Case>();
		try {
			prestate = my_conn.prepareStatement("SELECT * FROM " +
					DatabaseConstants.CASEINFO + " WHERE CaseNumber IN "
					+ "(SELECT CaseNumber FROM CaseStatus WHERE PullStatus = 2);");
			ResultSet rs = prestate.executeQuery();
			prestate = my_conn.prepareStatement("SELECT * FROM " +
					DatabaseConstants.LOCATION + " WHERE CaseNumber IN "
					+ "(SELECT CaseNumber FROM CaseStatus WHERE PullStatus = 2);");
			ResultSet locationRS = prestate.executeQuery();
			prestate = my_conn.prepareStatement("UPDATE " + DatabaseConstants.STATUS + 
					" SET PullStatus = " + DBC.WAVED + " WHERE PullStatus = " +
					DBC.READY_TO_BE_PULLED + ";");
			log.log(Level.INFO, "Updating PullStatus of waved cases");
			prestate.execute();
			do {
				
				rs.next();
				locationRS.next();
				if(rs.isBeforeFirst()){
					break;
				}
				String caseNumber = rs.getString(1);
				int height = rs.getInt(2);
				int length = rs.getInt(3);
				int width = rs.getInt(4);
				int locationX = locationRS.getInt(2);
				int locationY = locationRS.getInt(3);
				int aisle = locationRS.getInt(4);

				Location locate = new Location(locationX, locationY, aisle);
				Case the_case = new Case(caseNumber, height, length, width,
						locate, Units.Centimeters);
				caseList.add(the_case);
			} while(!rs.isLast());
		} catch(SQLException e){
			log.log(Level.SEVERE, e.getMessage()); //should log not throw...oh well for now
		}
		return caseList;
	}

	/**
	 * Updates the new location of a case in the DB.
	 * @param the_case_number The case number of the case.
	 * @param the_location The new Location of the case.
	 * @return A boolean if successful or not.
	 */
	public boolean updateLocation(String the_case_number, Location the_location){
		log.log(Level.FINE, "Calling updateLocation: " + the_case_number);
		PreparedStatement prestate;
		boolean check;
		try {
			prestate = my_conn.prepareStatement("UPDATE " + DatabaseConstants.LOCATION + 
					" SET X=" + the_location.my_x +", Y=" 
					+ the_location.my_x + ", Aisle=" + the_location.my_aisle 
					+ " WHERE CaseNumber = '" + the_case_number +"';");
			prestate.execute();
			check = true;
		} catch (SQLException e) {
			check = false;
			log.log(Level.SEVERE, e.getMessage());
		}
		return check;
	}

	/**
	 * Updates the status of the case in the DB.
	 * @param the_case_number The case number of the case.
	 * @param the_pull_status The new pulled status of the case.
	 * @return A boolean if successful or not.
	 */
	public boolean updatePullStatus(String the_case_number, int the_pull_status){
		log.log(Level.FINE, "Updating PullStatus for: " + the_case_number +
				" to : " + the_pull_status);
		PreparedStatement prestate;
		boolean check;
		try {
			prestate = my_conn.prepareStatement("UPDATE CaseStatus SET PullStatus = "
					+ "'" + the_pull_status + "' WHERE CaseNumber = '" + the_case_number +"';");
			prestate.execute();
			check = true;
		} catch (SQLException e) {
			check = false;
			log.log(Level.SEVERE, e.getMessage());
		}
		return check;

	}

}