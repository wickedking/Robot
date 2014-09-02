import server.Server;
import database.Database;


public class Main {

	
	public static void main(final String[] the_args){
		Database db = Database.getInstance();
		Server server = Server.getInstance();
		server.wave();
		System.out.println(server.toString());
		//System.out.println(server.getCase(3));
		//System.out.println(server.toString());
		
	}
}
