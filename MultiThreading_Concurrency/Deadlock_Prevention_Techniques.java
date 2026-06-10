import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private final String name;
    private int balance;

    public BankAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }
};

class TransferTask implements Runnable {
    private final BankAccount from;
    private final BankAccount to;
    private final int amount;

    public TransferTask(BankAccount from, BankAccount to, int amount) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " locked " + from.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " locked " + to.getName());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from " + from.getName() + " to " + to.getName());
            }
        }
    }
};

class DeadlockSimpleExample {
    public static void main(String[] args) {
        BankAccount accountA = new BankAccount("Account-A", 1000);
        BankAccount accountB = new BankAccount("Account-B", 1000);

        Thread t1 = new Thread(new TransferTask(accountA, accountB, 100), "T1");
        Thread t2 = new Thread(new TransferTask(accountB, accountA, 200), "T2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println("Finished");

    }
};

class LockOrderingSimple {

    static class Resource {
        int id;
        int value;

        public Resource(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Resource r1 = new Resource(101, 500);
        Resource r2 = new Resource(102, 300);

        Runnable task1 = () -> transfer(r1, r2, 50);
        Runnable task2 = () -> transfer(r2, r1, 30);

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }

    public static void transfer(Resource a, Resource b, int amount) {
        Resource[] locks = new Resource[] { a, b };

        // Sort by unique ID - guarantees a consistent global lock order
        Arrays.sort(locks, (x, y) -> Integer.compare(x.id, y.id));

        synchronized (locks[0]) {
            System.out.println(Thread.currentThread().getName() + " locked " + locks[0].id);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }

            synchronized (locks[1]) {
                System.out.println(Thread.currentThread().getName() +
                        " locked " + locks[1].id);
                System.out.println("Transferred " + amount +
                        " from " + a.id + " to " + b.id);
            }
        }
    }
}

class TryLockWithTimeout {

    static class Resource {
        final int id;
        final ReentrantLock lock = new ReentrantLock();

        public Resource(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);

        Runnable task1 = () -> tryTransfer(r1, r2);
        Runnable task2 = () -> tryTransfer(r2, r1);

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }

    public static void tryTransfer(Resource a, Resource b) {
        boolean acquiredA = false;
        boolean acquiredB = false;

        try {
            acquiredA = a.lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (acquiredA) {
                System.out.println(Thread.currentThread().getName() +
                        " locked Resource " + a.id);
                Thread.sleep(50);

                acquiredB = b.lock.tryLock(100, TimeUnit.MILLISECONDS);
                if (acquiredB) {
                    System.out.println(Thread.currentThread().getName() +
                            " locked Resource " + b.id);
                    System.out.println("Transfer successful between " +
                            a.id + " and " + b.id);
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            " could not lock Resource " + b.id +
                            " - backing off");
                }
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " could not lock Resource " + a.id +
                        " - backing off");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (acquiredB)
                b.lock.unlock();
            if (acquiredA)
                a.lock.unlock();
        }
    }
}

class MinimizeNestedLocking {

    static class SharedResource {
        private final Object lock = new Object();
        private int data;

        public void update(int value) {
            synchronized (lock) {
                data = value;
                System.out.println(Thread.currentThread().getName() +
                        " updated data to " + value);
            }
        }

        public int read() {
            synchronized (lock) {
                return data;
            }
        }
    }

    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        // Thread 1 does operations separately to avoid nested locking
        Runnable task1 = () -> {
            resource1.update(100); // Lock 1 used and released quickly
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
            resource2.update(200); // Lock 2 used separately
        };

        // Thread 2 also performs updates separately
        Runnable task2 = () -> {
            resource2.update(300);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
            resource1.update(400);
        };

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }
}

public class Deadlock_Prevention_Techniques {
    public static void main(String[] args) {
        DeadlockSimpleExample.main(args);
    }
}
