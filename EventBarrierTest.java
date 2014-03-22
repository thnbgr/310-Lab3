import static org.junit.Assert.*;

import org.junit.Test;


public class EventBarrierTest {

	@Test
	public void testArrive() {

		EventBarrier e = new EventBarrier();
		
		MyThread t1 = new MyThread(e);

		MyThread t2 = new MyThread(e);

		t1.start();

		t2.start();
		
		try {
			e.raise();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		assertTrue(e.waiters() == 2);

		
		
	}


}
