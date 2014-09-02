package server;

import java.util.LinkedList;
import java.util.List;

import database.Database;

/**
 * Main class that controls the information between the robots and the DB
 * Class is a singleton.
 * @author Cody
 *
 */
public class Server {
	
	private static final int ROBOT_QUANTITY = 5;

	private static final int LIST_QUANTITY = 5;

	private int my_robot_number;

	private Task[] aisles;
	
	/**
	 * The only instance of this server.
	 */
	private static Server me;
	
	private static Database my_db;

	/**
	 * @return The only instance of this class.
	 */
	public static Server getInstance(){
		if (me == null){
			me = new Server();
		}
		return me;
	}

	/**
	 * Default constructor
	 * @param robot_number the number of robots currently being served by this program.
	 */
	private Server(){
		my_robot_number = ROBOT_QUANTITY;
		aisles = new Task[my_robot_number];
		setupTasks();
		my_db = Database.getInstance();
	}
	
	private void setupTasks(){
		for(int i = 0; i < my_robot_number; i++){
			aisles[i] = new Task();
		}
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
	 * Splits a list of cases accordingly
	 * @param the_cases
	 * @return a boolean if every case was split correctly
	 */
	private boolean splitCases(final List<Case> the_cases){
		//TODO refactor to better represent an error for bad case aisles.
		if(the_cases.size() < 1){
			return false;
		}
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
	 * Returns the next case in the task. Can be null.
	 * @param the_aisle The aisle of the task to be polled. 
	 * @return The next case in the task. Can be null.
	 */
	public Case getCase(final int the_aisle){
		return aisles[the_aisle].getCase();
	}

	/**
	 * Resize the aisles array and perform a movement from the old array to the new one. 
	 * @return a boolean if successful
	 */
	private boolean resizeAisles(){
		//TODO
		return false;
	}
	
	/**
	 * Grabs new cases from the database to be pulled and splits them to the different aisles. 
	 */
	public void wave(){
		splitCases(my_db.getPullCases());
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < aisles.length; i++){
			sb.append(aisles[i].toString());
			sb.append("\n");
		}
		sb.append("END of SERVER\n");
		return sb.toString();
	}





}
