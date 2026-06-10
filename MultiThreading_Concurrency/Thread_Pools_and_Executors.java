import java.util.concurrent.*;
import java.util.*;

/* 
Let's say we are building a ride-matching system like Uber. Every time a ride request comes in,
we spin a new Thread to match the rider to a driver.
*/

/* 
class RideMatchingService {
    public void requestRide(String riderId) {
        Thread matchThread = new Thread(() -> {
            System.out.println("Matching rider " + riderId + " with a driver...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Ride matched for rider " + riderId);
        });
        matchThread.start();
    }
};
*/

class EmailService {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10); // Thread pool with 10 threads

    public static void sendEmail(String recipient) {
        executor.execute(() -> {
            System.out.println("Sending email to " + recipient + " on " + Thread.currentThread().getName());
            try {
                // Simulate dummy work (sending an email)
                Thread.sleep(1000);  // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Handle interruption
            }
            System.out.println("Email sent to " + recipient);
        });
    }

    public static void main(String [] args) {
        for(int i = 1; i <= 25; i++) {
            sendEmail("user" + i + "@example.com");
        }
        executor.shutdown();  // Shutdown the executor after submitting all tasks
    }
};

class Thread_Pools_and_Executors {
    public static void main(String[] args) {
        EmailService.main(args);
    }
};