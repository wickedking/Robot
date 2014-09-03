package server;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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
	 * Returns the case at the head of the task. Removes it from the task.
	 * @return The next case in the task.
	 */
	public Case getCase(){
		if(tasks.isEmpty()){
			return null;
		}
		try {
			Case box = tasks.removeFirst();
			return box;
		} catch (NoSuchElementException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Will sort the task based on the ordering of the locations. 
	 * @return A boolean if successful.
	 */
	public boolean sortTaskLocation(){
		Collections.sort(tasks, new LocationComparator());
		return true;
	}

	/**
	 * Unimplemented method to use an advanced sort based on a priority.
	 * @return A boolean if successful.
	 */
	public boolean sortTaskPriority(){
		//TODO
		//Sort task first on priority and then location within priority
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Task: \n");
		for(Case box : tasks){
			sb.append(box.toString());
			sb.append("\n");
		}
		return sb.toString();
	}



}
