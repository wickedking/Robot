package server;

/**
 * Holds all the revelent info about a case. Class is immutable.
 * @author cody
 *
 */
public final class Case {
	
	/**
	 * The case number of the case.
	 */
	public final String my_case_number;
	
	/**
	 * The height of the case. 
	 */
	public final int my_height;
	
	/**
	 * The width of the case.
	 */
	public final int my_width;
	
	/**
	 * the Length of the case.
	 */
	public final int my_length;
	
	/**
	 * The location of the case. 
	 */
	public final Location my_location;
	
	/**
	 * The type of unit being used for the dimensions.
	 */
	public final Units my_unit;
	
	public Case(final String the_case_number, final int the_height, final int the_width,
			final int the_length, final Location the_location, final Units the_unit){
		
		my_case_number = the_case_number;
		my_height = the_height;
		my_width = the_width;
		my_length = the_length;
		my_location = the_location;
		my_unit = the_unit;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(my_case_number);		
		return sb.toString();
	}

}
