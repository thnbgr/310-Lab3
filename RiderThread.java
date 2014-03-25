import java.io.File;
import java.util.Scanner;

public class RiderThread extends Thread {
	EventBarrier eventGettingOn;
	EventBarrier eventGettingOff;
	Building myBuilding;
	Elevator myElevator;
	Scanner s;
	int myId;
	int myStartingFloor;
	int myDestinationFloor;
	int myRiderNumber;

	public RiderThread(int id, Building b, Scanner sc) {
		myId = id;
		s = sc;
		myBuilding = b;
	}

	public void run() {
		while (s.hasNextLine()) {
			String input = s.nextLine();
			String[] values = input.split(" ");
			myRiderNumber = Integer.parseInt(values[0]);
			myStartingFloor = Integer.parseInt(values[1]);
			myDestinationFloor = Integer.parseInt(values[2]);

			if (myRiderNumber != myId)
				continue;
			// Deciding whether to call up or down
			
			callAndArrive();
			
			while (!myElevator.Enter()) {
				try {
					long start = System.nanoTime();
					LogWriter.log("R" + myRiderNumber + " cannot enter E"
							+ myElevator.elevatorId + " on F" + myStartingFloor+" due to capacity",
							start);
					eventGettingOn.complete(); // waiting for all to enter
					// what if the successful rider completes first?
					// what if unsuccessful rider completes first?
					System.out.println(myRiderNumber+" completes fail");

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				callAndArrive(); // calls this and it never returns

			}
			
			
			try {
				long start = System.nanoTime();
				LogWriter.log("R" + myRiderNumber + " enters E"
						+ myElevator.elevatorId + " on F" + myStartingFloor,
						start);
				eventGettingOn.complete();
				System.out.println(myRiderNumber+" completes success");

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // waiting for all to enter
			

			myElevator.RequestFloor(myDestinationFloor);
			
			long start = System.nanoTime();
			LogWriter.log("R" + myRiderNumber + " pushes E"
					+ myElevator.elevatorId + "B" + myDestinationFloor, start);

			try {
				eventGettingOff = myBuilding.getOffBarriers[myElevator.elevatorId][myDestinationFloor];
				eventGettingOff.arrive();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				myElevator.Exit();
				start = System.nanoTime();
				LogWriter.log("R" + myRiderNumber + " exits E"
						+ myElevator.elevatorId + " on F" + myDestinationFloor,
						start);

				eventGettingOff.complete(); // waiting for all to enter

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void callAndArrive() {
		if (myDestinationFloor > myStartingFloor) {
			// CALL SCHEDULE FUNCTION
			long start = System.nanoTime();
			LogWriter.log("R" + myRiderNumber + " pushes " + "U"
					+ myStartingFloor, start);
			myElevator = myBuilding.CallUp(myStartingFloor);
		} else {
			// CALL SCHEDULE FUNCTION
			long start = System.nanoTime();
			LogWriter.log("R" + myRiderNumber + " pushes " + "D"
					+ myStartingFloor, start);
			myElevator = myBuilding.CallDown(myStartingFloor);
		}

		try {
			myElevator.myEventBarrier.raise();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Align the rider with the appropriate event barrier and wait on that
		// until the elevator comes and does OpenDoor to raise the barrier
		try {
			eventGettingOn = myBuilding.getOnBarriers[myElevator.elevatorId][myStartingFloor];
// we know for sure that the unsuccessful rider pauses on here
			eventGettingOn.arrive(); // wait on barrier
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}