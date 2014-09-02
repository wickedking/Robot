package logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {
	
	private static PrintWriter my_pw ;
	
	public static void start(){
		try {
			my_pw = new PrintWriter(new File("db_log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void log(final String the_message){
		my_pw.println(the_message);
	}

}
