package server;

public enum Units {
	
	Inches,
	Milimeters,
	Centimeters;
	
	/**
	 * Does not override toString, but returns a custom string. 
	 */
	public String toString(Units the_unit){
		StringBuilder sb = new StringBuilder();
		switch (the_unit) {
		
		case Inches:
			sb.append("in");
			break;
			
		case Milimeters:
			sb.append("mm");
			break;
			
		case Centimeters:
			sb.append("cm");
			break;
			
		default:
			break;
		}
		return sb.toString();
	}
	
	

}
