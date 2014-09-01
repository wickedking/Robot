package database;
/**
 * Interface between client side and data side. 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import server.Case;
import server.Location;
import server.Units;


/**
 * Provides a set of methods for the client side to retrieve information out of the database.
 * 
 * @author Mike Westbrook
 * @author Cody Shafer
 * @version 2.2
 */
public class Database{

	/**
	 * Maximum number of papers that can be assigned to a reviewer or subprogram chair.
	 */
	public static final int MAXPAPERS = 4;

	/**
	 * The only instance of this database.
	 */
	private static Database me;

	/**
	 * Actual connection to the database.
	 */
	private final Connection my_conn;

	/**
	 * Cannot be directly instantiated.
	 */
	protected Database(){
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
	 * @return
	 */
	private static Connection getConnection() {
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
				e.printStackTrace();
			}
		} 
		return conn;
	}
	
	

	public List<Case> getPullCases(){
		PreparedStatement prestate;
		List<Case> caseList = new ArrayList<Case>();
		try {
			prestate = my_conn.prepareStatement("SELECT * FROM CaseInfo WHERE CaseNumber IN (SELECT CaseNumber FROM status WHERE PullStatus = 2);");
			ResultSet rs = prestate.executeQuery();
			prestate = my_conn.prepareStatement("SELECT * FROM location WHERE CaseNumber IN (SELECT CaseNumber FROM status WHERE PullStatus = 2);");
			ResultSet locationRS = prestate.executeQuery();
			do {
				rs.next();
				locationRS.next();
				String caseNumber = rs.getString(1);
				int height = rs.getInt(2);
				int length = rs.getInt(3);
				int width = rs.getInt(4);
				int locationX = locationRS.getInt(2);
				int locationY = locationRS.getInt(3);
				int aisle = locationRS.getInt(4);
				
				Location locate = new Location(locationX, locationY, aisle);
				Case the_case = new Case(caseNumber, height, length, width, locate, Units.Centimeters);
				caseList.add(the_case);
			} while(!rs.isLast());
		} catch(SQLException e){
			e.printStackTrace(); //should log not throw...oh well for now
		}
		return caseList;
	}
	
	public boolean updateLocation(String the_case_number, Location the_location){
		//TODO
		return false;
	}
	
	public boolean updatePullStatus(String the_case_number, int the_pull_status){
		//TODO
		return false;
	}


}