import java.io.File;
import java.util.Scanner;

public class RiderThread extends Thread {
	EventBarrier eventGettingOn;
	EventBarrier eventGettingOff;
	Building myBuilding;
	Elevator myElevator;
	Scanner s;
	int myId;
	
	public RiderThread(int id, Building b, Scanner sc) {
		myId = id;
		s = sc;
		myBuilding = b;
	}

	public void run() {
	    while (s.hasNextLine()) {
	    	String input = s.nextLine();
	    	String[] values = input.split(" ");
	    	int riderNumber = Integer.parseInt(values[0]);
	    	int startingFloor = Integer.parseInt(values[1]);
	    	int destinationFloor = Integer.parseInt(values[2]);

	    	if (riderNumber != myId) continue;
	    	
		// Deciding whether to call up or down
		if(destinationFloor > startingFloor)
		{
			// CALL SCHEDULE FUNCTION
			myElevator = myBuilding.CallUp(startingFloor);
		}
		else
		{
			// CALL SCHEDULE FUNCTION
			myElevator = myBuilding.CallDown(startingFloor);
		}

		try {
			myElevator.myEventBarrier.raise();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Align the rider with the appropriate event barrier and wait on that until the elevator comes and does OpenDoor to raise the barrier
		try {
			eventGettingOn = myBuilding.getOnBarriers[myElevator.elevatorId][startingFloor];
			eventGettingOn.arrive(); // wait on barrier
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			myElevator.myEventBarrier.raise();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if(!myElevator.Enter()) {
				// deal with max capacity
			} else {
				eventGettingOn.complete(); // waiting for all to enter
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		myElevator.RequestFloor(destinationFloor);
		

		try {
			eventGettingOff = myBuilding.getOffBarriers[myElevator.elevatorId][destinationFloor];
			eventGettingOff.arrive();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			myElevator.Exit();

			eventGettingOff.complete(); // waiting for all to enter

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	    }
	}
}