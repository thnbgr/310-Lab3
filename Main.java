
public class Main {
	public static void main(String[] args) throws InterruptedException {
		
EventBarrier e = new EventBarrier();
		
		MyThread t1 = new MyThread(e, false);

		MyThread t2 = new MyThread(e, true);

		t1.start();

		t2.start();
		
		
		System.out.println(e.waiters());
	}
}
