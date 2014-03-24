public class EventBarrier extends AbstractEventBarrier {

    protected int numArrived; // number that come to the gate
	protected int numWaiters; // number that go to sleep because the gate is closed
	protected int numCompleted; // number that cross while the gate is open
	protected int numComplete; // number that finish calling complete()
	protected boolean barrierClosed; // closed, down
	protected boolean crossing; // numCrossed - numArrive > 0 -- stragglers or threads are still trying to cross, don't move on to complete

	public EventBarrier()
	{
		numArrived = 0;
		numWaiters = 0;
		numCompleted = 0;
		numComplete = 0;
		barrierClosed = true;
		crossing = false;
	}

	@Override
	public synchronized void arrive() throws InterruptedException
	{
		// TODO Auto-generated method stub
		numArrived++;

		if (barrierClosed)
		{
			numWaiters++;
			wait();
		}

		//System.out.println("awoke");
		// If the thread reaches here, then it can start crossing
		numCompleted++;
		return;
	}

	@Override
	public synchronized void raise() throws InterruptedException {
		// TODO Auto-generated method stub
		barrierClosed = false;
		notifyAll();

		while (numCompleted < numArrived) // there are still more crossing
		{
			wait();
		}
		barrierClosed = true;
		numArrived = 0;
		numWaiters = 0;
		numCompleted = 0;
	}

	@Override
	public synchronized void complete() throws InterruptedException {

		numCompleted++;
		if (numCompleted == numArrived) {
			notifyAll();
		} else {
			wait();
		}
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub		
		return numWaiters;
	}

}
