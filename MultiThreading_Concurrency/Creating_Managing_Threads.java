import java.util.concurrent.*;
import java.util.*;

class OrderService {
    public static void main(String args[]) {
        System.out.println("Placing order...");
        sendSMS();
        System.out.println("task 1 done");
        sendEmail();
        System.out.println("task 2 done");
        String eta = calculateETA();
        System.out.println("Order placed. ETA: " + eta);
        System.out.println("task 3 done");
    }


    private static void sendSMS() {
        try {
            Thread.sleep(2000); // simulate delay
            System.out.println("SMS sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail() {
        try {
            Thread.sleep(3000); // simulate delay
            System.out.println("Email sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String calculateETA() {
        try {
            Thread.sleep(5000); // simulate delay
        } catch(InterruptedException e) {
            e.printStackTrace();
            return "unknown";
        }
        return "25 minutes";
    }
}

class SMSThread extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(2000); // simulate delay
            System.out.println("SMS sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
};

class EmailThread extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(3000); // simulate delay
            System.out.println("Email sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
};


class SMSThreadRunnable implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(2000); // simulate delay
            System.out.println("SMS sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
};


class EmailThreadRunnable implements Runnable {
    @Override
    public void run() {
        try{
            Thread.sleep(3000); // simulate delay
            System.out.println("Email sent!");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
};

// Implementing Callable to calculate ETA (only this task requires a return value)
class ETACalculationTask implements Callable<String> {
    private final String location;

    public ETACalculationTask(String location) {
        this.location = location;
    }

    @Override
    public String call() throws InterruptedException {
        Thread.sleep(5000); // Simulate 5-second delay for ETA calculation
        System.out.println("Calculation ETA using Callable.");
        return "ETA: 25 minutes"; // Return the ETA result
    }
}


public class Creating_Managing_Threads {
    public static void main(String[] args) {
        // OrderService.main(args);

        /* // Method 1: Using Thread class
        SMSThread smsThread = new SMSThread();
        EmailThread emailThread = new EmailThread();
        smsThread.start();
        emailThread.start();
        */

        // Method 2: Using Runnable interface
        Thread smsThread = new Thread(new SMSThreadRunnable());
        Thread emailThread = new Thread(new EmailThreadRunnable());

        // Method 3: Using the Callable and Future interface
        FutureTask<String> etaThreadRunnable = new FutureTask<>(new ETACalculationTask("BLR"));
        Thread etaThread = new Thread(etaThreadRunnable);

        /* // Method 4: Using anonymous inner class or lambda expression and using directly Runnable interface without creating a separate class
        Runnable  r = new Runnable () {
            @Override
            public void run() {
                sendSMS();
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        // OR we can also do like this using a lambda expression

        Thread thread = new Thread(() -> sendSMS());
        thread.start();
        */

        System.out.println("Task started...");
        smsThread.start();
        System.out.println("Task1 ongoing...");
        emailThread.start();
        System.out.println("Task 2 ongoing...");
        etaThread.start();

        try {
            smsThread.join(); // wait for SMS thread to finish
            emailThread.join(); // wait for Email thread to finish
            String eta = etaThreadRunnable.get(); // wait for ETA calculation to finish and get result
            System.out.println(eta);
            System.out.println("All tasks completed.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
};
