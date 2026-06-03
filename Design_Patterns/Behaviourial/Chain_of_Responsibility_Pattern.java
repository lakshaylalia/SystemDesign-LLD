import java.util.*;

/* 
Suppose im a customer support system for an e-commerce platform like Amazon, user raise tickets. These tickets could be: 
    1. General Enquiry
    2. Refund Request
    3. Technical Issue
    4. Complaints about delivery
*/

/* class SupportService {
    public void handleRequest(String type) {
        if (type.equals("general")) {
            System.out.println("Handled by General Support.");
        } else if (type.equals("refund")) {
            System.out.println("Handled by Refund Support.");
        } else if (type.equals("technical")) {
            System.out.println("Handled by Technical Support.");
        } else if (type.equals("delivery")) {
            System.out.println("Handled by Delivery Support.");
        } else {
            System.out.println("No hanlder available for this type of request.");
        }
    }
}; */

/*
    1. Violates OCP: Every time a new type is added, the method must be edited.
    2. Monolithic Code: All the logic in single class.
    3. Not flexible or scalable: Cannot change the order of processing withut modifying core logic.
 */


// Abstract class defining the SupportHandler
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    // Method to set the next handler in the chain
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

     // Abstract method to handle the request
    public abstract void handleRequest(String requestType);
};

// Concrete Handler for General Support
class GeneralSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("general")) {
            System.out.println("GeneralSupport: Handling general query");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
};

// Concrete Handler for Billing Support
class BillingSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("refund")) {
            System.out.println("BillingSupport: Handling refund request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
};

// Concrete Handler for Technical Support
class TechnicalSupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("technical")) {
            System.out.println("TechnicalSupport: Handling technical issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

// Concrete Handler for Delivery Support
class DeliverySupport extends SupportHandler {
    public void handleRequest(String requestType) {
        if (requestType.equalsIgnoreCase("delivery")) {
            System.out.println("DeliverySupport: Handling delivery issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        } else {
            System.out.println("DeliverySupport: No handler found for request");
        }
    }
}

public class Chain_of_Responsibility_Pattern {
    public static void main(String args[]) {

        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();

        // Setting up the chain: general -> billing -> technical -> delivery
        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);



        // Testing the chain of responsibility with different request types
        general.handleRequest("refund");
        general.handleRequest("delivery");
        general.handleRequest("unknown");
    }
}
