
public abstract class AbstractEventBarrier {
    
    /**
     * Instantiate the stateless AbstractEventBarrier.
     */
	public AbstractEventBarrier() {
	}

	/**
	 * Arrive at the barrier and wait until an event is signaled. Return
 	 * immediately if already in the signaled state.
	 * @throws InterruptedException 
 	 */
	public abstract void arrive() throws InterruptedException;

	/**
	 * Signal the event and block until all threads that wait for this
 	 * event have responded. The EventBarrier returns to an unsignaled state
 	 * before raise() returns.
	 * @throws InterruptedException 
 	 */	
	public abstract void raise() throws InterruptedException;
	
	/**
	 * Indicate that the calling thread has finished responding to a
 	 * signaled event, and block until all other threads that wait for 
 	 * this event have also responded.
	 * @throws InterruptedException 
 	 */
	public abstract void complete() throws InterruptedException;

	/**
	 * Return a count of threads that are waiting for the event or that
 	 * have not responded yet.
 	 */
	public abstract int waiters();
<<<<<<< HEAD
}
=======
}
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
