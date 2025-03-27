package architsi_CSCI201_Assignment2;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Trade extends Thread {
	public static int rejections = 0;
	Task task;
	AgentSemaphore agentSemaphore;
	static Lock sharedBalanceLock = new ReentrantLock();
	long start;
	
	
	public Trade(long start, Task task, AgentSemaphore agentSemaphore) {
		this.task = task;
		this.agentSemaphore = agentSemaphore;
		this.start = start;
	}
	
	
	public void run() {
		
		try {
			if((task.getTime() * SecondsFormat.millisecsPerSec) - (System.currentTimeMillis() - start) > 0) {
				Thread.sleep((task.getTime() * SecondsFormat.millisecsPerSec) - (System.currentTimeMillis() - start));
			}
			
			
			if(agentSemaphore.acquireAgent()) {
				
				if(task.getTicketQty() > 0) {
					System.out.println("[" + SecondsFormat.getSecondsFormat(start) + "]" + " Starting purchase of " + task.getTicketQty() + " exhibit tickets of " + task.getName());
					Thread.sleep(2000);
					sharedBalanceLock.lock();
					
					if(main.balance >= task.getPrice() * task.getTicketQty()) {
						main.balance -= task.getPrice() * task.getTicketQty();
						
						System.out.println("[" + SecondsFormat.getSecondsFormat(start) + "]" + " Finished purchase of " + task.getTicketQty() + " exhibit tickets of " + task.getName() + "." + " Current Balance after trade: " + main.balance);
					}
					else {
						rejections++;
						System.out.println("[" + SecondsFormat.getSecondsFormat(start) + "]" + " Unsuccesful purchase of " + task.getTicketQty() + " exhibit tickets of " + task.getName() + " due to insufficient balance. Current Balance: " + main.balance);
					}
				
				
					sharedBalanceLock.unlock();
						
				}
				else if(task.getTicketQty() < 0) {
					System.out.println("[" + SecondsFormat.getSecondsFormat(start) + "]" + " Starting Sale of " + Math.abs(task.getTicketQty()) + " exhibit tickets of " + task.getName());
					Thread.sleep(3000);
					sharedBalanceLock.lock();
					
					main.balance += Math.abs(task.getPrice() * task.getTicketQty());
					System.out.println("[" + SecondsFormat.getSecondsFormat(start) + "]" + " Finished sale of " + Math.abs(task.getTicketQty()) + " exhibit tickets of " + task.getName() + "." + " Current Balance after trade: " + main.balance);
			
				
					sharedBalanceLock.unlock();
					
				}
				
	
			}
			else{
				System.out.println("Wait for agent");
			}
		}
		catch(InterruptedException e) {
			System.out.println("Interrupted");
		}
		finally {
			agentSemaphore.releaseAgent();
			
		}
	}
	
	
}
