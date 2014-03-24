public class RiderThread extends Thread {
	EventBarrier eventGettingOn;
	EventBarrier eventGettingOff;
	int myFromFloor, myToFloor;
	Building myBuilding;
	Elevator myElevator;
	
	public RiderThread(int fromFloor, int toFloor, Building b) {
		myFromFloor = fromFloor;
		myToFloor = toFloor;
		myBuilding = b;
	}

	public void run() {
		// enclose this all in a while loop

		// Deciding whether to call up or down
		if(myToFloor > myFromFloor)
		{
			// CALL SCHEDULE FUNCTION
			myElevator = myBuilding.CallUp(myFromFloor);
		}
		else
		{
			// CALL SCHEDULE FUNCTION
			myElevator = myBuilding.CallDown(myFromFloor);
		}

		// Align the rider with the appropriate event barrier and wait on that
	}
}