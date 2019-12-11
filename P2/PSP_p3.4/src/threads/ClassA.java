package threads;

import java.util.HashSet;
import java.util.Set;

public class ClassA {
	
	private int counter;
	
	public ClassA(int counter){
		this.counter=counter;
	}
	
	private Set<Long> threadlds = new HashSet<Long>();

	public void enterAndWait() {
			
		System.out.println("soy el hilo: " + Thread.currentThread().getName());
		threadlds.add(Thread.currentThread().getId());
		counter-=1;
		
		System.out.println("He acabado de ejecutarme");

	}
	
	
	public Set<Long> getSet() {
		return threadlds;
	}
	
	public boolean isFinished() {
		if(counter == 0) {
			return true;
		}else {
			return false;
		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
}
