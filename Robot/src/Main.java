import server.Case;
import server.Server;
import database.Database;


public class Main {

	
	public static void main(final String[] the_args){
		Database db = Database.getInstance();
		Server server = Server.getInstance();
		server.wave();
		System.out.println(server.toString());
		Case box = server.getCase(3);
		System.out.println(box);

		
	}
}
