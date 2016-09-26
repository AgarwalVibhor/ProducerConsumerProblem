package thread;

import java.util.Vector;

public class Test {
	
	public static void main(String[] args) {
		
		Vector<Integer> vector = new Vector<Integer>();
		int size = 4;
		
		Producer producer = new Producer(vector, size);
		Consumer consumer = new Consumer(vector);
		
		Thread prodThread = new Thread(producer, "Producer");
		Thread consThread = new Thread(consumer, "Consumer");
		
		prodThread.start();
		consThread.start();
		
	}

}
