package server;

import java.util.LinkedList;
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
	 * Default constructor
	 * @param robot_number the number of robots currently being served by this program.
	 */
	public Server(final int robot_number){
		my_robot_number = robot_number;
		aisles = new Task[my_robot_number];
	}

	/**
	 * Changes the total number of robots.
	 * @param the_robot_number The new Number of robots
	 * @return A boolean if successful
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
	 * Receive case and splits it to the robots accordingly
	 * @param the_case The case to be added
	 * @return A boolean if successful
	 */
	public boolean splitCase(final Case the_case){
		int aisle = the_case.my_location.my_aisle;
		if (aisle > my_robot_number){
			return false;
		}
		aisles[aisle].addCase(the_case);
		return true;
	}

	/**
	 * Splits a list of cases accordingly
	 * @param the_cases
	 * @return a boolean if every case was split correctly
	 */
	public boolean splitCase(final List<Case> the_cases){
		//TODO refactor to better represent an error for bad case aisles.
		int aisle;
		boolean check = true;
		for(Case box : the_cases){
			aisle = box.my_location.my_aisle;
			if (aisle > my_robot_number){
				check = false;
				continue;
			}
			aisles[aisle].addCase(box);
		}
		return check;
	}

	public List<Case> getCases(final int the_aisle){
		//TODO
		//Check if within aisle number and send back a certain number of cases or null.
		LinkedList<Case> boxes = new LinkedList<Case>();
		for(int i = 0; i < LIST_QUANTITY; i++){
			boxes.add(aisles[the_aisle].getCase());
		}
		return boxes;
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
