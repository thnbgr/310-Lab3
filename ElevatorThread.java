public class ElevatorThread extends Thread {
	Elevator myElevator;

	public ElevatorThread(Elevator elev) {
		myElevator = elev;
	}

	public void run() {
		try {
			myElevator.myEventBarrier.arrive();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			myElevator.myEventBarrier.complete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!myElevator.requests.isEmpty()) { // keep going as long as there
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

			
			}
		}

	}
}