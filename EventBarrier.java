
public class EventBarrier extends AbstractEventBarrier {

	private boolean barrierClosed;
	private int numArrive;
	private int numComplete;
	
	public EventBarrier() {
		barrierClosed = false;
		numArrive = 0;
		numComplete = 0;
	}
	
	@Override
	public synchronized void arrive() throws InterruptedException {
		// Externally called
		numArrive++;
		while (barrierClosed) {
			wait();
		}
	}

	@Override
	public synchronized void raise() {
		// Internally called
		barrierClosed = false;
		notifyAll();
		while (numArrive > numComplete) {
			System.out.println(numComplete);
		}
		barrierClosed = true;
		numArrive = 0;
		numComplete = 0;
	}

	@Override
	public void complete() {
		numComplete++;
	}

	@Override
	public int waiters() {
		return numArrive-numComplete;
	}

}
