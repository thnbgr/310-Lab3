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
