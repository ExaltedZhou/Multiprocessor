package bakery;
import java.util.concurrent.atomic.*;

/**
 * Exercise 1:
 * Here you will create a new kind of lock that is based on the concept of the Bakery
 * lock from chapter 2. This one, however, will use atomics.
 */
       
public class Bakeryoid implements Lock{
    /**
     * We need two atomic integers here. The first one will
     * give us the next ticket to wait on. The second
     * tells us the next ticket to run.
     */
    
    AtomicIntegerArray ticket;
    AtomicIntegerArray entering;
    private int threads;
    private static AtomicBoolean BUSY;

    public Bakeryoid(int threads){
        this.threads=threads;
        ticket = new AtomicIntegerArray(threads);
        entering = new AtomicIntegerArray(threads);
        BUSY = new AtomicBoolean(false);
    }
    private int getTicket() {
        int max=0;
        for (int i = 0; i < threads; i++){
            if (ticket.get(i) > max) {
                max = ticket.get(i) ;
            }
        }
        return max;
    }
    
    /**
     * Get the next ticket to wait on, then wait
     * (using a while loop) for that ticket to be
     * the next to run.
     */
    public void lock() {
        int i = ThreadID.get();
        entering.set(i, 1);
        ticket.set(i, 1 + getTicket());
        while(BUSY.get()){}
        for(int k = 0; k < threads;i++){
            while((k != i) && entering.get(k)==1 && ((ticket.get(k) < ticket.get(i)) || ((ticket.get(i) == ticket.get(k)) && k < i))){}  
        }
        BUSY.set(true);
     }
    
    /**
     * Advance to the next running ticket.
     */
    public void unlock() {
        
        BUSY.set(false);
        entering.set(ThreadID.get(), 0);
    }
}