package server;

import java.util.List;

/**
 * Main class that controls the information between the robots and the DB
 * @author Cody
 *
 */
public class Server {
	
	private static final int LIST_QUANTITY = 5;

	private int my_robot_number;
	
	private Task[] aisles;

	/**
	 * Default construtor
	 * @param robot_number the number of robots currently being served by this program.
	 */
	public Server(final int robot_number){
		my_robot_number = robot_number;
		aisles = new Task[my_robot_number];
	}

	/**
	 * 
	 * @param the_robot_number
	 * @return
	 */
	public boolean changeRobotNumber(int the_robot_number){
		if(the_robot_number > 0){
			my_robot_number = the_robot_number;
			resizeAisles();
			return true;
		}
		return false;
	}
	
	
	/**
	 * Recieve case and splits it to the robots accordingly
	 * @param the_case The case to be added
	 * @return A boolean if successful
	 */
	public boolean splitCase(final Case the_case){
		//TODO
		return false;
	}
	
	/**
	 * Splits a list of cases accordingly
	 * @param the_cases
	 * @return
	 */
	public boolean splitCase(final List<Case> the_cases){
		//TODO
		return false;
	}
	
	public List<Case> getCases(final int the_aisle){
		//TODO
		//Check if within aisle number and send back a certain number of cases or null.
		return null;
	}
	
	
	/**
	 * Resize the aisles array and perform a movement from the old array to the new one. 
	 * @return a boolean if successful
	 */
	private boolean resizeAisles(){
		//TODO
		return false;
	}





}
