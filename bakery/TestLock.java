package bakery;

import java.util.*;

public class TestLock {
    volatile static int counter = 0;
    final static int NTHREADS = 15;
    final static int NINCRS = 10;
    
    public static void main(String[] args) throws Exception {
        final Lock lock = new Bakeryoid(NTHREADS);
        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<NTHREADS;i++) {
            threads.add(new Thread(()->{
                for(int k=0;k<NINCRS;k++) {
                    lock.lock();
                    try {
                        int c = counter;
                        try { Thread.sleep(1); }
                        catch (InterruptedException ie) {}
                        counter = c+1;
                    } finally {
                        lock.unlock();
                    }
                }
            }));
        }
        long t1 = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long t2 = System.currentTimeMillis();
        System.out.println("Counter: "+counter);
        System.out.printf("Time: %.2f secs%n",(0.001*(t2-t1)));
        assert counter == NTHREADS*NINCRS;
    }
}