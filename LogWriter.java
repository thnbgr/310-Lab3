import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class LogWriter {

	public static void log(String str) {
	    PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(
					(new BufferedWriter(new FileWriter("output.log", true)))
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    printWriter.println(str);
	    printWriter.close ();
	}
}
