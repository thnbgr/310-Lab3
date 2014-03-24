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

		// Align the rider with the appropriate event barrier and wait on that until the elevator comes and does OpenDoor to raise the barrier
		try {
			eventGettingOn = myBuilding.getOnBarriers[myElevator.elevatorId][myFromFloor];
			eventGettingOn.arrive(); // wait on barrier
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Once the door is open, the rider can enter - the OpenDoor() should result in the eventGettingOn barrier to raise()

		try {
			if(!myElevator.Enter()) {} else {
				/// ENTER CODE - if false, wait for the elevator to leave
				// another event barrier specific for failing to enter the elevator due to capacity issue should wait on the 
			eventGettingOn.complete(); // waiting for all to enter
			}
					// WHEN DOES SLEEP HAPPEN?
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		myElevator.RequestFloor(myToFloor);

		try {
			eventGettingOff = myBuilding.getOffBarriers[myElevator.elevatorId][myToFloor];
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