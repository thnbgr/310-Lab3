
public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		EventBarrier e = new EventBarrier();
		
		Thread t1 = new MyThread(e);

		Thread t2 = new MyThread(e);

		t1.start();

		t2.start();

		e.raise();
		
	}
}
