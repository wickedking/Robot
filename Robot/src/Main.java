import server.Case;
import server.Server;
import database.Database;


public class Main {

	
	public static void main(final String[] the_args){
		Server server = Server.getInstance();
		//server.wave();
		System.out.println(server.toString());

	}
}
