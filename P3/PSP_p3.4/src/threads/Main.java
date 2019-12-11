package threads;

import java.util.ArrayList;

public class Main {		
	
	private static final int THREADS = 20;

		public static void main(String[] args) {

			Thread[] threads = new Thread[THREADS];
			ClassA classA = new ClassA(20);
			ArrayList<ClassB> listaB = new ArrayList<ClassB>();
			

			for (int i = 0; i < THREADS; ++i) {

				ClassB f = new ClassB(classA);
				listaB.add(f);

			}
			
			for (int i = 0; i < THREADS; ++i) {
				if(i!=listaB.size()-1) {
					listaB.get(i).setNext(listaB.get(i+1));
				}else {
					listaB.get(i).setNext(listaB.get(0));
					break;
				}

			}
			
			for (int i = 0; i < THREADS; ++i) {
				threads[i] = new Thread(listaB.get(i));

					threads[i].start();

			}
					
			
			synchronized(listaB.get(0)) {
				listaB.get(0).notify();
			}
			
			
			for (int i = 0; i < THREADS; ++i) {
				
				try {
					System.out.println("aqui ya no llego");
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			System.out.println(classA.getCounter());
			System.out.println(classA.getSet().toString());
	}
	
}
