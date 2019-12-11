package threads;

public class MyTask implements Runnable{

	private Counter counter;
	
	private long random = (long) (Math.random() * 1000);
	
	public MyTask(Counter counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		
		System.out.println("Hilo: "+ Thread.currentThread().getName() + " y tengo el numero " + counter.getIncrement());
		try {
			Thread.sleep(random);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		counter.increment();
		
	}

}
