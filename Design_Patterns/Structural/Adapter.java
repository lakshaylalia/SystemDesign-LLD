import java.util.*;

interface PaymentGateway {
    void pay(String orderId, double amount);

}

class RazorpayAPI {
    public void makePayment(String orderId, double amount) {
        System.out.println("Processing payment of " + amount + " for order " + orderId + " through Razorpay Gateway.");
    }
}

class PayPalAPI {
    public void makePayment(String orderId, double amount) {
        System.out.println("Processing payment of " + amount + " for order " + orderId + " through PayPal Gateway.");
    }
}

class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Processing payment of " + amount + " for order " + orderId + " through PayU Gateway.");
    }
}

class RazorPayAdapter implements PaymentGateway {
    private RazorpayAPI razorpayAPI;

    public RazorPayAdapter() {
        this.razorpayAPI = new RazorpayAPI();
    }

    @Override
    public void pay(String orderId, double amount) {
        razorpayAPI.makePayment(orderId, amount);
    }
}

class PayPalAdapter implements PaymentGateway {
    private PayPalAPI payPalAPI;

    public PayPalAdapter() {
        this.payPalAPI = new PayPalAPI();
    }

    @Override
    public void pay(String orderId, double amount) {
        payPalAPI.makePayment(orderId, amount);
    }
}

class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        paymentGateway.pay(orderId, amount);
    }
}

public class Adapter {
    
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService(new PayUGateway());
        checkoutService.checkout("ORDER123", 250.75);
        CheckoutService checkoutService1 = new CheckoutService(new RazorPayAdapter());
        checkoutService1.checkout("ORDER456", 500.00);
        CheckoutService checkoutService2 = new CheckoutService(new PayPalAdapter());
        checkoutService2.checkout("ORDER789", 1000.50);
    }
}
