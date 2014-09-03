package toRobot;

import constants.DBC;
import server.Case;
import server.Server;
import database.Database;

/**
 * Will send case information to specific robot, and will receive communication from same.
 * @author Cody Shafer
 *
 */
public class RobotCommunicator {

	/**
	 * Holds the case number of the last case sent to the robot.
	 */
	private Case[] my_aisle_cases = new Case[5];

	/**
	 * The instance for the database.
	 */
	private Database my_db = Database.getInstance();

	/**
	 * The instance for the server. 
	 */
	private Server my_server = Server.getInstance();
	

	/**
	 * This method is used when moving cases within the robots/aisles. 
	 * @param the_aisle The aisle of the case.
	 * @return The next case to be pulled. 
	 */
	public Case getCaseMovement(final int the_aisle){
		my_db.updatePullStatus(my_aisle_cases[the_aisle].my_case_number, DBC.READY_TO_PUTAWAY);
		return my_server.getCase(the_aisle);
	}

	/**
	 * Used when pulling a case for a non robot area. Will update DB with a new status. 
	 * @param the_aisle The aisle of the case. 
	 * @return The next case to be pulled. 
	 */
	public Case getCase(final int the_aisle) {
		//TODO handle initial start up with empty case in aisle
		my_db.updatePullStatus(my_aisle_cases[the_aisle].my_case_number, DBC.BEEN_PULLED);
		Case box = my_server.getCase(the_aisle);
		my_aisle_cases[the_aisle] = box;
		return box;
	}

	//TODO
	/*
	 * Will contain logic to send information to robot using correct protocol.
	 * Will contain logic to receive information from robot.
	 * Will send the information to the correct robot.
	 * Will receive a confirmation of a pull status.
	 * Will receive a location for a case put away, along with case number.
	 * For case putaway, connect to the ToDB with case number and location.
	 * 
	 */

	//get more cases(robot number)


}
