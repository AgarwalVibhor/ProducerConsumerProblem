package thread;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer implements Runnable {
	
	private Vector<Integer> sharedQueue;
	
	public Consumer(Vector<Integer> sharedQueue)
	{
		this.sharedQueue = sharedQueue;
	}

	@Override
	public void run() {
		
		try {
			System.out.println("Consumed :" + consume());
		} catch (InterruptedException e) {
			Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null);
		}

	}
	
	public int consume() throws InterruptedException
	{
		int element = -1;
	
			if(sharedQueue.isEmpty())
			{
				synchronized (sharedQueue) {
					
					System.out.println("Queue is empty. " + Thread.currentThread().getName() + " is waiting.");
					System.out.println("Size of the queue is :" + sharedQueue.size());
					sharedQueue.wait();
				}
			}
			else
			{
				synchronized (sharedQueue) {
					System.out.println("In here....");
					element = (Integer) sharedQueue.remove(0);
					sharedQueue.notifyAll();
				}
			}
		
		return element;
	}

}
