package thread;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable{
	
	private Vector<Integer> sharedQueue;
	private int size;
	
	Producer(Vector<Integer> sharedQueue, int size)
	{
		this.sharedQueue = sharedQueue;
		this.size = size;
	}
	
	@Override
	public void run(){
		
		for(int i = 0; i < 7; i++)
		{
			System.out.println("Produced :" + i);
			try
			{
				produce(i);
			}
			catch(InterruptedException e)
			{
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null);
			}
		}
		
	}
	
	public void produce(int i) throws InterruptedException
	{
		if(sharedQueue.size() == size)
		{
			synchronized (sharedQueue) {
				
				System.out.println("Queuw is full. " + Thread.currentThread().getName() + " is waiting.");
				System.out.println("Size of the queue is :" + sharedQueue.size());
				sharedQueue.wait();
			}
		}
		else
		{
			synchronized (sharedQueue) {
				
				sharedQueue.add(i);
				sharedQueue.notifyAll();
			}
		}
	}

}
