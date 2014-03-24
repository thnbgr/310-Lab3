import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		LogWriter.flush();
		String file = "src/requests.txt";
		Scanner sc = new Scanner(new File(file));
	    String header = sc.nextLine();
	    String[] values = header.split(" ");
	    int numFloors = Integer.parseInt(values[0]);
	    int numElevators = Integer.parseInt(values[1]);
	    int numRiders = Integer.parseInt(values[2]);
	    int numMaxCapacity = Integer.parseInt(values[3]);
	    
	    Building myBuilding = new Building(numFloors, numElevators, numMaxCapacity);
	    
	    for (int i = 1; i <= numRiders; i++) {
			Scanner scan = new Scanner(new File(file));
		    scan.nextLine();
		    RiderThread rider = new RiderThread(i, myBuilding, scan);
		    rider.start();
	    }
	}
}
