package threads;

public class Main {		
	
	private static final int THREADS = 20;

		public static void main(String[] args) {

			Thread[] threads = new Thread[THREADS];
			ClassA classA = new ClassA();


			for (int i = 0; i < THREADS; ++i) {
				threads[i] = new Thread(new ClassB(classA));
				threads[i].start();

			}
			
	}
	
}
