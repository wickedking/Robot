package server;

/**
 * Holds the info for a location. Class is immutable.
 * @author cody
 *
 */
public final class Location {
	
	/**
	 * The X value of the location.
	 */
	public final int my_x;
	
	/**
	 * The Y value of the location.
	 */
	public final int my_y;
	
	/**
	 * The aisle of the location.
	 */
	public final int my_aisle;
	
	/**
	 * Default constructor 
	 * @param the_x The X value of the location.
	 * @param the_y The Y value of the location.
	 * @param the_aisle The aisle of the location.
	 */
	public Location(final int the_x, final int the_y, final int the_aisle){
		my_x = the_x;
		my_y = the_y;
		my_aisle = the_aisle;
	}
	
	/**
	 * Change the X value of the location.
	 * @param the_x The new X value.
	 * @return The new Location with the changed X value.
	 */
	public Location changeX(final int the_x){
		return new Location(the_x, my_y, my_aisle);
	}
	
	/**
	 * Change the Y value of the location
	 * @param the_y The new Y value.
	 * @return The new Location with the changed Y value.
	 */
	public Location changeY(final int the_y){
		return new Location(my_x, the_y, my_aisle);
	}
	
	/**
	 * Change the Aisle value of the location.
	 * @param the_aisle The new Aisle value.
	 * @return The new location with the Aisle changed. 
	 */
	public Location changeAisle(final int the_aisle){
		return new Location(my_x, my_y, the_aisle);
	}
	
	/**
	 * Change some, all or none of the values of the Location.
	 * @param the_x The new X value.
	 * @param the_y The new Y value.
	 * @param the_aisle The new Aisle value.
	 * @return The new location with the changed values. 
	 */
	public Location changeAll(final int the_x, final int the_y, final int the_aisle){
		return new Location(the_x, the_y, the_aisle);
	}
	

}
