package philosophers;
import java.util.Random;
import java.util.concurrent.locks.*;


public class Philosopher extends Thread {
	private int phiTotal;
	private int phiID;
	final Lock lock=new ReentrantLock();
	final Condition cond=lock.newCondition();
	private Status s;
	
	public enum Status{
		THINKING,HUNGRY,EATING;
	}
	
	public Philosopher(int phiTotal,int phiID) {
		this.phiTotal=phiTotal;
		this.phiID=phiID;
		s=Status.THINKING;
	}
	
	public Status getStatus() {
		return s;
	}
	
	public void takes(int i) {
		lock.lock();
		try {
			s=Status.HUNGRY;
			test(i);
			if(s!=Status.EATING) {
				cond.await();
			}
		} catch (InterruptedException ie) {}
		finally {
			lock.unlock();
		}
	}
	
	public void returns(int i) {
		s=Status.THINKING;
		test((i+1)%phiTotal);
		test((i+phiTotal-1)%phiTotal);
	}
	
	public void test(int i) {
		int nb1=(i+phiTotal-1)%phiTotal;
		int nb2=(i+1)%phiTotal;
		Philosopher p1 = new Philosopher(phiTotal,nb1);
		Philosopher p2 = new Philosopher(phiTotal,nb2);
		if(p1.getStatus()!=Status.EATING && p2.getStatus()!=Status.EATING && s==Status.HUNGRY) {
			s=Status.EATING;
			cond.signalAll();
		}
		
	}
	
	public void run() {
		Random r = new Random();
		while(true) {
			try {
				sleep(r.nextInt(2000)+1000);
				System.out.println("Philospoher "+ phiID + " is Hungry");
				takes(phiID);
				sleep(r.nextInt(2000)+1000);
				System.out.println("Philospoher "+ phiID + " is Eating");
				returns(phiID);
				sleep(r.nextInt(2000)+1000);
				System.out.println("Philospoher "+ phiID + " returns and is Thinking");
			}	catch(InterruptedException ie) {}
		}
	}
}


