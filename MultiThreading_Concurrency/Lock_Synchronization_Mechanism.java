import java.util.concurrent.locks.*;
import java.util.concurrent.*;

class TicketBooking {
    private int availableSeats = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public void bookTicket(String user) {
        System.out.println(user + " is trying to book...");
        lock.lock(); // blocks if already held the another thread
        try {
            System.out.println(user + " acquired lock.");
            if(availableSeats > 0) {
                System.out.println(user + " successfully booked the ticket");
                availableSeats--;
            } else {
                System.out.println(user + " could not book the ticket. No seats left");
            }
        } finally {
            System.out.println(user + " is releasing the lock.");
            lock.unlock();   
        }
    }
};

class ExpiringReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean isLocked = false;

    public boolean tryLoackWithExpiry(long timeoutMillis) {
        boolean accquired = lock.tryLock(); // don't wait lock is already taken
        if(accquired) {
            isLocked = true;

            // schedule unlock after timeout
            scheduler.schedule(()-> {
                if(lock.isHeldByCurrentThread() || isLocked) {
                    System.out.println("Auto releasing lock after timeout");
                    unlockSafely();

                }
            }, timeoutMillis, TimeUnit.MILLISECONDS);
        }

        return accquired;
    }

    public void unlockSafely() {
        if(lock.isHeldByCurrentThread() || isLocked) {
            isLocked = false;
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public boolean isLocked() {
        return lock.isLocked();
    }
};


// Booking system that waits up to 2 s for a lock
class TicketBookingTryLock {

    // shared resource: initial seat count
    private int availableSeats = 1;

    // exclusive lock protecting seat updates
    private final ReentrantLock lock = new ReentrantLock();

    // attempts to book a ticket for the given user
    public void bookTicket(String user) {
        System.out.println(user + " is trying to book...");

        // remember whether we actually got the lock
        boolean lockAcquired = false;
        try {
            // wait up to 2 s before giving up
            lockAcquired = lock.tryLock(2, TimeUnit.SECONDS);

            if (lockAcquired) {
                System.out.println(user + " acquired lock.");

                // critical section – safe to inspect/update seats
                if (availableSeats > 0) {
                    System.out.println(user + " successfully booked the ticket.");
                    availableSeats--;
                } else {
                    System.out.println(user + " could not book the ticket. No seats left.");
                }

                // simulate a long operation that holds the lock
                // this helps demonstrate the timeout behavior for the next user
                Thread.sleep(3000); 
            } else {
                System.out.println(user + " could not acquire lock. Try again later.");
            }
        } 
        catch (InterruptedException e) {
            // restore interrupt status and log
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } 
        finally {
            // release only if we were the owner
            if (lockAcquired) {
                System.out.println(user + " is releasing the lock.");
                lock.unlock();
            }
        }
    }
}


// Shared stock price data guarded by a ReadWriteLock
class StockData {
    private double price = 100.0; 
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    // Writer: updates price
    public void updatePrice(double newPrice) {
        lock.writeLock().lock();  // acquire write lock
        try {
            System.out.printf("%s updating price to %.2f%n",
                    Thread.currentThread().getName(), newPrice);
            price = newPrice;
        } finally {
            lock.writeLock().unlock(); // release write lock
        }
    }

    // Reader: reads price
    public void readPrice() {
        lock.readLock().lock();   // acquire read lock
        try {
            System.out.printf("%s read price: %.2f%n",
                    Thread.currentThread().getName(), price);
        } finally {
            lock.readLock().unlock();  // release read lock
        }
    }
}

class TUFPlusAccount {
    // fixed number of login permits (tokens)
    private final Semaphore deviceSlots;

    // constructor sets permit count
    public TUFPlusAccount(int maxDevices) {
        this.deviceSlots = new Semaphore(maxDevices);
    }

    // user attempts to log in
    public boolean login(String user) throws InterruptedException {
        System.out.println(user + " trying to log in...");

        // try to grab a permit immediately (non-blocking)
        if (deviceSlots.tryAcquire()) {
            System.out.println(user + " successfully logged in.");
            return true;
        } else {
            System.out.println(user + " denied login - too many devices.");
            return false;
        }
    }

    // user logs out and returns the permit
    public void logout(String user) {
        System.out.println(user + " logging out.");
        deviceSlots.release();  // release permit for next device
    }
}


public class Lock_Synchronization_Mechanism {
    public static void main(String[] args) {
        TicketBookingTryLock booking = new TicketBookingTryLock();

        // first user attempts booking immediately
        Thread user1 = new Thread(() -> booking.bookTicket("User 1"));

        // second user arrives 500 ms later and may time-out
        Thread user2 = new Thread(() -> {
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            booking.bookTicket("User 2");
        });

        user1.start();
        user2.start();
    }
}