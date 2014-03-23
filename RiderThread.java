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

		// Align the rider with the appropriate event barrier and wait on that until the elevator comes and does OpenDoor to raise the barrier
		try {
			eventGettingOn = myBuilding.getOnBarriers[myFromFloor][myElevator.elevatorId];
			eventGettingOn.arrive();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Once the door is open, the rider can enter

		try {
			myElevator.Enter(); // wth does this do
			eventGettingOn.complete();
			myElevator.RequestFloor(myToFloor);	
					// WHEN DOES SLEEP HAPPEN?
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			eventGettingOff = myBuilding.getOffBarriers[myToFloor][myElevator.elevatorId];
			eventGettingOff.arrive();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}