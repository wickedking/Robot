import server.Server;
import toRobot.RobotCommunicator;


public class Main {

	
	public static void main(final String[] the_args){
		Server server = Server.getInstance();
		RobotCommunicator rc = RobotCommunicator.getInstance();
		server.wave();
		System.out.println(rc.getCase(3));

	}
}
