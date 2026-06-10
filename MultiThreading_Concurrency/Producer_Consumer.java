import java.util.LinkedList;
import java.util.Queue;

// Shared resource: CoffeeMachine acts as a buffer of size 1
class CoffeeMachine {
    // Flag to indicate buffer status: true → coffee ready, false → cup empty
    private boolean isCoffeeReady = false;

    // Method to be run by the producer thread
    public synchronized void makeCoffee() throws InterruptedException {
        // If coffee is already ready, producer must wait (buffer is full)
        while (isCoffeeReady) {
            // Releases lock and waits until notified by consumer
            wait();
        }

        // Simulate coffee preparation
        System.out.println("Brewing coffee…");
        Thread.sleep(1000); // Simulate time to make coffee

        // Set the buffer to full (coffee is now ready)
        isCoffeeReady = true;
        System.out.println("Coffee ready!");

        // Notify the consumer thread that coffee is available
        notify();
    }

    // Method to be run by the consumer thread
    public synchronized void drinkCoffee() throws InterruptedException {
        // If no coffee is available, consumer must wait (buffer is empty)
        while (!isCoffeeReady) {
            // Releases lock and waits until notified by producer
            wait();
        }

        // Simulate coffee consumption
        System.out.println("Drinking coffee…");
        Thread.sleep(1000); // Simulate time to drink coffee

        // Set the buffer to empty (coffee has been consumed)
        isCoffeeReady = false;
        System.out.println("Cup emptied - brew next!");

        // Notify the producer thread that it can brew again
        notify();
    }
}

// Driver class
class ProducerConsumerProblem {
    public static void main(String[] args) {
        // Shared CoffeeMachine object acts as the synchronized monitor
        CoffeeMachine machine = new CoffeeMachine();

        // Producer thread that continuously makes coffee
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    machine.makeCoffee(); // Try to produce coffee
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread that continuously drinks coffee
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    machine.drinkCoffee(); // Try to consume coffee
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }
};

// Represents a code submission by a user
class Submission {
    private static int idCounter = 1; // Used to generate unique submission IDs
    private final int submissionId;
    private final String userName;

    public Submission(String userName) {
        this.userName = userName;
        this.submissionId = idCounter++; // Auto-incrementing ID
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public String getUserName() {
        return userName;
    }
}

// Shared resource between producers (users) and consumers (judges)
class SubmissionQueue {
    private final Queue<Submission> queue = new LinkedList<>(); // Shared buffer
    private final int MAX_CAPACITY = 5; // Bounded buffer size

    // Producer logic: User submits a solution
    public synchronized void submit(Submission submission) throws InterruptedException {
        // If queue is full, producer waits
        while (queue.size() == MAX_CAPACITY) {
            System.out.println("⏳ Queue full. " + submission.getUserName() + " is waiting to submit.");
            wait(); // Releases lock and waits for space
        }

        // Add submission to the queue
        queue.offer(submission);
        System.out.println("" + submission.getUserName() + " submitted code: #" + submission.getSubmissionId());

        notifyAll(); // Notifies judges that a new task is available
    }

    // Consumer logic: Judge processes a submission
    public synchronized Submission consume(String judgeName) throws InterruptedException {
        // If queue is empty, consumer waits
        while (queue.isEmpty()) {
            System.out.println("△ " + judgeName + " waiting for submissions...");
            wait(); // Releases lock and waits for submissions
        }

        // Remove a submission from the queue
        Submission sub = queue.poll();
        System.out.println("⚙️ " + judgeName + " started evaluating submission #" +sub.getSubmissionId() + " from " + sub.getUserName());

        notifyAll(); // Notifies waiting producers if queue was full
        return sub;
    }
}


public class Producer_Consumer {
    
}
