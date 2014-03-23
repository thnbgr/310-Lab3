public class ElevatorThread extends Thread {
	EventBarrier event;
	int currentFloor;

	public ElevatorThread(EventBarrier e, Elevator elev) {
		event = e;
		currentFloor = elev.currentFloor;
	}

	public void run() {

		try {
			event.arrive();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// for (int i = 0;i<1000000;i++) {}

		try {
			event.complete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}