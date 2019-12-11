package threads;

public class ClassB implements Runnable{

	ClassA classA = new ClassA();
	
	public ClassB (ClassA classA) {
		this.classA=classA;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		classA.enterAndWait();
	}

}
