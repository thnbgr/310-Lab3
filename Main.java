
public class Main {
	public static void main(String[] args) throws InterruptedException {
		
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
		
		System.out.println(e.waiters());
	}
}
