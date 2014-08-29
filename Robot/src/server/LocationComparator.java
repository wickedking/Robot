package server;

import java.util.Comparator;

public class LocationComparator implements Comparator<Location>{

	@Override
	public int compare(Location first, Location second) {
		if(first.my_x < second.my_x){
			return 1;
		} else if (first.my_x > second.my_x){
			return -1;
		} else {
			return first.my_y - second.my_y;
		}
	}

}
