import java.util.concurrent.atomic.AtomicInteger;

class PurchaseCounter {
    private int count = 0;

    public void increment() {
        // read and increment count
        count++; // no thread-safe
    }
    
    public int getCount() {
        return count;
    }
};


class PurchaseCounterSyncMethod {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
};


class PurchaseCounterSyncBlock {
    private int count = 0;

    public void increment() {
        synchronized(this) {
            count++;
        }
    }
    
    public int getCount() {
        return count;
    }
};


class PurchaseCounterVolatile {
    // Only uses when only one thread write another read, doesn't make it atomic i.e not thread safe
    private volatile int count = 0;

    public void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
};

class PurchaseAtomicCounter {
    private AtomicInteger likes = new AtomicInteger(0);

    public void incrementLikes() {
        int prev, next;
        do {
            prev = likes.get();
            next = prev+1;
        } while(!likes.compareAndSet(prev, next));
    }

    public int getLikes() {
        return likes.get();
    }
};

class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        // PurchaseCounter counter = new PurchaseCounter();
        // PurchaseCounterSyncMethod counter = new PurchaseCounterSyncMethod();
        // PurchaseCounterSyncBlock counter = new PurchaseCounterSyncBlock();
        PurchaseCounterVolatile counter = new PurchaseCounterVolatile();


        Runnable task = () -> {
            for(int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        /* This will fails because the count is not thread-safe */

        System.out.println("Final Count: " + counter.getCount());
    }

};


public class Thread_Safety_Synchronization {
    public static void main(String[] args) {
    }
}
