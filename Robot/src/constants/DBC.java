package constants;

/**
 * Constants for use in calling Database methods.
 * @author Cody Shafer
 *
 */
public class DBC {
	
	/**
	 * The case status after it has been pulled.
	 */
	public static final int BEEN_PULLED = 4;
	
	/**
	 * The case status when it is ready to be pulled.
	 */
	public static final int READY_TO_BE_PULLED = 2;
	
	/**
	 * The case status when it is ready to be putaway.
	 */
	public static final int READY_TO_PUTAWAY = 0;
	
	/**
	 * the case status when it is already in location. 
	 */
	public static final int IN_LOCATION = 1;
	
	/**
	 * The case status when it has been waved but not yet pulled. 
	 */
	public static final int WAVED = 3;

}
