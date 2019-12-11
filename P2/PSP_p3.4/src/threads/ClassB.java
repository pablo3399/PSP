package threads;

public class ClassB implements Runnable{

	private final ClassA eaw;
	
	private ClassB next;
	
	public ClassB (ClassA eaw) {
		this.eaw=eaw;
	}
	
	@Override
	public void run() {
			
		while(eaw.isFinished()==false) {
			
		synchronized (this) {
			try {
				
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		if(eaw.isFinished()==false) {
				 eaw.enterAndWait();
				 }
				synchronized(next) {
					 next.notify();
			}
		}
		
	}

	public ClassB getNext() {
		return next;
	}

	public void setNext(ClassB next) {
		this.next = next;
	}
	
	

}
