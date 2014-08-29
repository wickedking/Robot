package server;

import java.util.LinkedList;
import java.util.List;

/**
 * Holds the cases for a certain group, for easy passing and holding.
 * @author Cody Shafer
 *
 */
public class Task {
	
	/**
	 * A List of cases, in an order. 
	 */
	private LinkedList<Case> tasks;
	
	/**
	 * Default no args constructor. 
	 */
	public Task(){
		tasks = new LinkedList<Case>();
		
	}
	
	/**
	 * Add a case to the task. Will add the case to the end of the task unless sort
	 * explicitly called. 
	 * @param The_case The case that is added to the task.
	 * @return A boolean if successful.
	 */
	public boolean addCase(final Case the_case){
		tasks.add(the_case);
		return true;
	}
	
	/**
	 * Add a list of cases to the task. Will add them all in the order of passing
	 * into the end of the task, unless sort is explicitly called. 
	 * @param the_list The list of cases to be added to the task.
	 * @return A boolean if successful.
	 */
	public boolean addCase(final List<Case> the_list){
		tasks.addAll(the_list);
		return true;
	}
	
	/**
	 * Will sort the task based on the ordering of the locations. 
	 * @return A boolean if successful.
	 */
	public boolean sortTaskLocation(){
		//TODO
		//Sort the task based on location alone
		return false;
	}
	
	public boolean sortTaskPriority(){
		//TODO
		//Sort task first on priority and then location within priority
		return false;
	}
	
	

}
