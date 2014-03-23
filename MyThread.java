<<<<<<< HEAD
public class MyThread extends Thread {
	EventBarrier event;

	public MyThread(EventBarrier e) {
		event = e;
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
=======

public class MyThread extends Thread {
	EventBarrier event;
	boolean gateKeeper;
	public MyThread(EventBarrier e, boolean g) {
		gateKeeper = g;
		event = e;
	}
	
	public void run() {
		if (!gateKeeper) {
			try {
				arrive();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				raise();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void arrive() throws InterruptedException {
		event.arrive();
		complete();
	}
	
	public void raise() throws InterruptedException {
		event.raise();
	}
	
	public void complete() throws InterruptedException {
		event.complete();
	}
}
>>>>>>> 6480f2c279f4c4d752567cd53d64663fe9a3e797
