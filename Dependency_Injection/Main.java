package Dependency_Injection;

interface PaymentService {
    void process(Order order);
    // void setPaymetGateway(PaymentGateway paymentGateway);  --> Interface Injection
}

class OrderItems {
    private String itemName;
    private int quantity;

    public OrderItems(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
}

class Order {
    private OrderItems[] items;
    private int totalAmount;

    public Order(OrderItems[] items, int totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
};

class InventoryService {
    void blockItems(Order order){
        System.out.println("Blocking items for order " + order);
    }
}

class NotificationService {
    void sendConfirmation(Order order) {
        System.out.println("Sending confirmation email for order " + order);
    }
}

class RazorpayPayment implements PaymentService {
    @Override
    public void process(Order order) {
        System.out.println("Processing payment of " + order + " through Razorpay Gateway.");
    }
};

class StripePayment implements PaymentService {
    @Override
    public void process(Order order) {
        System.out.println("Processing payment of " + order + " through Stripe Gateway.");
    }
};

/*
Problems:
1. Hardcoded logic - tightly couples OrderService with specific implementations
2. Difficult to test - how to test OrderService without hitting payment APIs?
3. Scalability Issues - what if we want to switch from Stripe to Razorpay? 
*/


class OrderService {
    private InventoryService inventory = new InventoryService()
    private PaymentService payment = new RazorpayPayment();
    private NotificationService notification = new NotificationService();

    public void checkout(Order order) {
        inventory.blockItems(order);
        payment.process(order);
        notification.sendConfirmation(order);
    }
};


// Dependency Injection
/* 
It is of 3 types
1. Constructor Injection: Immutable Dependencies, test-friendly, enforces required dependencies
2. Setter Injection: Mutable Dependencies, test-friendly, can be misused by not calling setter methods
3. Interface Injection: Rarely used, needs changes to the interface itself(by generic methods)
*/

/*
Advantages:
1. Swappable components
2. Testable with mocks
3. follows Dependency Inversion Principle
4. Open to extension (new payment types), closed to modification
*/


class OrderService2 {
    private InventoryService inventory;
    private PaymentService payment;
    private NotificationService notification;

    // Constructor Injection
    public OrderService2(InventoryService inventory, PaymentService payment, NotificationService notification) {
        this.inventory = inventory;
        this.payment = payment;
        this.notification = notification;
    }

    // Setter Injection
    public void setInventory(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void checkout(Order order) {
        inventory.blockItems(order);
        payment.process(order);
        notification.sendConfirmation(order);
    }
};

// Technical Way to implement Dependency Injection

interface NotificationService2 {
    void send(String message);
};

class EmailNotificationService implements NotificationService2 {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
};

class WhattsAppNotificationService implements NotificationService2 {
    @Override
    public void send(String message) {
        System.out.println("Sending message: " + message);
    }
}

class UserService {
    private NotificationService2 notificationService;

    public UserService(NotificationService2 notificationService) {
        this.notificationService = notificationService;
    }

    public void register(String user) {
        System.out.println("Registering user: " + user);
        notificationService.send("User registered: " + user);
    }
}

/*
When not to use Dependency Injection
1. Tinly classes with zero dependencies
2. Static utility classes
3. One off scripts or tools
*/


/*
When to use Dependency Injection
1. Classes use new for internal collaborators  ---> Constructor Injection
2. Cannot mock the services in tests  ---> Inject dependency via Interface
3. Adding feature breaks all code ---> Use abstraction and inject
4. Too many if|switch for service types ---> Inject Strategy implementation
*/

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}