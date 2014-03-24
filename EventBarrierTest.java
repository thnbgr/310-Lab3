import static org.junit.Assert.*;

import org.junit.Test;


public class EventBarrierTest {

	@Test
	public void testArrive() throws InterruptedException {

		EventBarrier e = new EventBarrier();
		
		MyThread t1 = new MyThread(e, false);

		MyThread t3 = new MyThread(e, true);

		t1.start();

		Thread.sleep(100);
		
		assertTrue(e.waiters() == 2);
		
		t3.start();
		
	
	}
}
