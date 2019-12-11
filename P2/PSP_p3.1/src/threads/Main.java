package threads;

public class Main {

	private static final int THREADS= 1080;
	
	public static void main(String[]args) {
		
		Thread[] threads = new Thread[THREADS];
		
		Counter counter = new Counter();
		
		for (int n = 0; n<THREADS; n++) {
			threads[n]=new Thread( new MyTask (counter));
			threads[n].start();
			
		}
		
		for (int n = 0; n<THREADS; n++) {

			try {
				
				threads[n].join();
				
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("El contador ha aumentado a " + counter.getIncrement());
	}
}
