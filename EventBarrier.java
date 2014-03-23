
public class EventBarrier extends AbstractEventBarrier {

<<<<<<< HEAD
	protected int numArrived; // number that come to the gate
	protected int numWaiters; // number that go to sleep because the gate is closed
	protected int numComplete; // number that finish calling complete()
	protected boolean barrierClosed; // closed, down
=======
    protected int numArrived; // number that come to the gate
	protected int numWaiters; // number that go to sleep because the gate is closed
	protected int numCompleted; // number that cross while the gate is open
	protected int numComplete; // number that finish calling complete()
	protected boolean barrierClosed; // closed, down
	protected boolean crossing; // numCrossed - numArrive > 0 -- stragglers or threads are still trying to cross, don't move on to complete
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797

	public EventBarrier()
	{
		numArrived = 0;
		numWaiters = 0;
<<<<<<< HEAD
		numComplete = 0;
		barrierClosed = true;
=======
		numCompleted = 0;
		numComplete = 0;
		barrierClosed = true;
		crossing = false;
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
	}
	
	@Override
	public synchronized void arrive() throws InterruptedException
	{
		// TODO Auto-generated method stub
		numArrived++;
<<<<<<< HEAD
		if(barrierClosed)
=======
		if (barrierClosed)
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
		{
			numWaiters++;
			wait();
		}
<<<<<<< HEAD
		
		// If the thread reaches here, then it can start crossing
		
=======
		//System.out.println("awoke");
		// If the thread reaches here, then it can start crossing
		numCompleted++;
		return;
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
	}

	@Override
	public synchronized void raise() throws InterruptedException {
		// TODO Auto-generated method stub
		barrierClosed = false;
		notifyAll();
<<<<<<< HEAD
		// Need some logic to keep the gate open until all consumers have finished running complete
		while(numComplete < numArrived) // there are still more crossing
=======
		
		while (numCompleted < numArrived) // there are still more crossing
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
		{
			wait();
		}
		barrierClosed = true;
		numArrived = 0;
<<<<<<< HEAD
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
=======
		numWaiters = 0;
		numCompleted = 0;
	}

	@Override
	public synchronized void complete() throws InterruptedException {
		
		numCompleted++;
		if (numCompleted == numArrived) {
			notifyAll();
		} else {
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
			wait();
		}
	}

	@Override
	public int waiters() {
		// TODO Auto-generated method stub		
		return numWaiters;
	}

}
