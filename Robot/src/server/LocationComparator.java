package server;

import java.util.Comparator;

public class LocationComparator implements Comparator<Case>{

	@Override
	public int compare(Case first, Case second) {
		if(first.my_location.my_x < second.my_location.my_x){
			return 1;
		} else if (first.my_location.my_x > second.my_location.my_x){
			return -1;
		} else {
			return first.my_location.my_y - second.my_location.my_y;
		}
	}

}
