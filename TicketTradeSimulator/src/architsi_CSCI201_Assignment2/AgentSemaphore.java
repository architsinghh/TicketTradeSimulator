package architsi_CSCI201_Assignment2;
import java.util.concurrent.Semaphore;

public class AgentSemaphore {

	Semaphore semaphore;
	
	public AgentSemaphore(int agentCount) {
		 semaphore = new Semaphore(agentCount);
	}
	
	public boolean acquireAgent() {
		try {
			semaphore.acquire();
			return true;
		}
		catch(InterruptedException e) {
			return false;
		}
			
	}
	
	public void releaseAgent() {
		semaphore.release();
	}

}
