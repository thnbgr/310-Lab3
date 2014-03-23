import static org.junit.Assert.*;

import org.junit.Test;


public class EventBarrierTest {

	@Test
<<<<<<< HEAD
	public void test() {
		EventBarrier e = new EventBarrier();
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		
	}

=======
	public void testArrive() throws InterruptedException {

		EventBarrier e = new EventBarrier();
		
		MyThread t1 = new MyThread(e, false);

		MyThread t2 = new MyThread(e, false);

		MyThread t3 = new MyThread(e, true);

		t1.start();

		t2.start();
		
		Thread.sleep(100);
		
		assertTrue(e.waiters() == 2);
		
		t3.start();
		
	
	}


>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
}
