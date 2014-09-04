import database.Database;
import server.Case;
import server.Location;
import server.Server;
import toRobot.RobotCommunicator;


public class Main {

	
	public static void main(final String[] the_args){
		Server server = Server.getInstance();
		RobotCommunicator rc = RobotCommunicator.getInstance();
		server.wave();
		Case box = rc.getCase(3);
		while(box != null){
			box = rc.getCase(3);
		}
		rc.getCase(4);
		Database db = Database.getInstance();
		db.updateLocation("box9", new Location(13, 13, 1));
		

	}
}
