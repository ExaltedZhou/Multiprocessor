package philosophers;

public class PhilosophersDriver {

	public static void main(String[] args) throws InterruptedException {
		int phiTotal = 25;
		Philosopher[] ps = new Philosopher[phiTotal];
		for (int i=0;i<phiTotal;i++) {
			ps[i]=new Philosopher(phiTotal,i);
			Thread t = new Thread(ps[i]);
			t.start();
		}
	}

}
