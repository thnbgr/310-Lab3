public class ElevatorThread extends Thread {
	EventBarrier event;
	Elevator myElevator;

	public ElevatorThread(EventBarrier e, Elevator elev) {
		event = e;
		myElevator = elev; 
	}

	public void run() {
		
		while (true) { // keep going as long as there are more requests
			
		myElevator.goToNextFloor();

		try {
			event.raise();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	}
}