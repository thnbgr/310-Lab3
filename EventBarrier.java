
public class EventBarrier extends AbstractEventBarrier {

	protected int numArrived; // number that come to the gate
	protected int numWaiters; // number that go to sleep because the gate is closed
	protected int numComplete; // number that finish calling complete()
	protected boolean barrierClosed; // closed, down

	public EventBarrier()
	{
		numArrived = 0;
		numWaiters = 0;
		numComplete = 0;
		barrierClosed = true;
	}
	
	@Override
	public synchronized void arrive() throws InterruptedException
	{
		// TODO Auto-generated method stub
		numArrived++;
		if(barrierClosed)
		{
			numWaiters++;
			wait();
		}
		
		// If the thread reaches here, then it can start crossing
		
	}

	@Override
	public synchronized void raise() throws InterruptedException {
		// TODO Auto-generated method stub
		barrierClosed = false;
		notifyAll();
		// Need some logic to keep the gate open until all consumers have finished running complete
		while(numComplete < numArrived) // there are still more crossing
		{
			wait();
		}
		barrierClosed = true;
		numArrived = 0;
		numComplete= 0;
		numWaiters = 0;
	}

	@Override
	public synchronized void complete() throws InterruptedException{
		// TODO Auto-generated method stub
		numComplete++;
		if(numComplete == numArrived) {
			notifyAll();
		}
		else{
			wait();
		}
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub		
		return numWaiters;
	}

}
