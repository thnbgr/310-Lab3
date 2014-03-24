public class ElevatorThread extends Thread {
	EventBarrier event;
	Elevator myElevator;

	public ElevatorThread(EventBarrier e, Elevator elev) {
		event = e;
		myElevator = elev;
	}

	public void run() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (!myElevator.requests.isEmpty()) { // keep going as long as there
													// are more requests
			// all requests have been processed
			myElevator.goToNextFloor();
			try {
				myElevator.myOffBarriers[myElevator.currentFloor].raise();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				myElevator.myOnBarriers[myElevator.currentFloor].raise();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// all riders have gotten on and requested floors

		}

	}
}