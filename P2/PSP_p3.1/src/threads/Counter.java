package threads;

public class Counter {

	private int counter = 0;
	
	public synchronized void increment() {
		 counter++;
	}
	
	public int getIncrement() {
		
		return counter;
		
	}
}
