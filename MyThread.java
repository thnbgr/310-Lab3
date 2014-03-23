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