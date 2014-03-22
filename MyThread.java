
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
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.complete();
	}
}
