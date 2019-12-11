package threads;

public class ClassA {

	private static final int WAIT = 1600;

	public synchronized void enterAndWait() {
		System.out.println("soy el hilo: " + Thread.currentThread().getName());
		try {
			Thread.sleep(WAIT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("He acabado de ejecutarme");
	}
}
