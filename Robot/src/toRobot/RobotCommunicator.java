package toRobot;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

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

	private static final Logger log = Logger.getLogger(RobotCommunicator.class.getName());

	private static RobotCommunicator me;


	/**
	 * @return The only instance of this class.
	 */
	public static RobotCommunicator getInstance(){
		if (me == null){
			me = new RobotCommunicator();
		}
		return me;
	}

	private RobotCommunicator(){
		try {
			FileHandler fh = new FileHandler("rc_log.log", false);
			fh.setFormatter(new SimpleFormatter());
			log.addHandler(fh);
			log.setLevel(Level.FINE);
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //Can't log cause logger blew up. 
		}
		log.log(Level.INFO, "Communicator created.");
	}


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
		log.log(Level.FINE, "Robot #" + the_aisle +" asked for a new box.");
		if (my_aisle_cases[the_aisle] != null){
			my_db.updatePullStatus(my_aisle_cases[the_aisle].my_case_number, DBC.BEEN_PULLED);
		}
		Case box = my_server.getCase(the_aisle);
		my_aisle_cases[the_aisle] = box;
		if(box != null){
			log.log(Level.FINE, "Robot #" + the_aisle +" is being sent " + box.my_case_number);
		} else {
			log.log(Level.FINE, "Robot #" + the_aisle +" is being sent a null case");
		}

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
