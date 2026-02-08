#include <bits/stdc++.h>
using namespace std;


class PaymentGateway {
    public:
    virtual void processPayment(double amount) = 0;
    virtual ~PaymentGateway() {}
};

class RazorpayGateway : public PaymentGateway {
    void processPayment(double amount) override {
        cout << "Processing INR payment using Razorpay ₹" << amount << endl;
    }
    virtual ~RazorpayGateway() {}
};

class PayUGateway : public PaymentGateway {
    void processPayment(double amount) override {
        cout << "Processing INR payment using PayU ₹" << amount << endl;
    }
    virtual ~PayUGateway() {}
};

class StripeGateway : public PaymentGateway {
    void processPayment(double amount) override {
        cout << "Processing INR payment using Stripe ₹" << amount << endl;
    }
    virtual ~StripeGateway() {}
};

class PayPalGateway : public PaymentGateway {
    void processPayment(double amount) override {
        cout << "Processing INR payment using PayPal ₹" << amount << endl;
    }
    virtual ~PayPalGateway() {}
};

class KomojuGateway: public PaymentGateway {
    void processPayment(double amount) override {
        cout << "Processing INR payment using Komoju" << amount << endl;
    }
    virtual ~KomojuGateway() {}
};




class Invoice {
    public:
    virtual void generateInvoice() = 0;
    virtual ~Invoice() {}
};

class GSTInvoice : public Invoice {
    public:
    void generateInvoice() override {
        cout << "Generating GST invoice for India." << endl;
    }
    ~GSTInvoice() {}
};

class USInvoice : public Invoice {
    public:
    void generateInvoice() override {
        cout << "Generating GST invoice for US." << endl;
    }
    ~USInvoice() {}
};

class JapanInvoice : public Invoice {
    public:
    void generateInvoice() override {
        cout << "Generating GST invoice for Japan." << endl;
    }
    ~JapanInvoice() {}
};




class RegionFactory {
    public:
    virtual PaymentGateway* createPaymentGateway(string gatewayType) = 0;
    virtual  Invoice* createInvoice() = 0;
    virtual ~RegionFactory() {}
};

class IndiaFactory: public RegionFactory {
    public:
    PaymentGateway* createPaymentGateway(string gatewayType) {
        transform(gatewayType.begin(), gatewayType.end(), gatewayType.begin(), ::tolower);
        if(gatewayType == "razorpay") {
            return new RazorpayGateway();
        } else if(gatewayType == "payu") {
            return new PayUGateway();
        }
        throw invalid_argument("Gateway type not supported in India.");
    }

    Invoice* createInvoice() {
        return new GSTInvoice();
    }
    ~IndiaFactory() {}
};

class USFactory: public RegionFactory {
    public:
    PaymentGateway* createPaymentGateway(string gatewayType) {
        transform(gatewayType.begin(), gatewayType.end(), gatewayType.begin(), ::tolower);
        if(gatewayType == "stripe") {
            return new StripeGateway();
        } else if(gatewayType == "paypal") {
            return new PayPalGateway();
        }
        throw invalid_argument("Gateway type not supported in US.");
    }

    Invoice* createInvoice() {
        return new USInvoice();
    }
    ~USFactory() {}
};

class JapanFactory: public RegionFactory {
    public:
    PaymentGateway* createPaymentGateway(string gatewayType) {
        transform(gatewayType.begin(), gatewayType.end(), gatewayType.begin(), ::tolower);
        if(gatewayType == "komojuGateway") {
            return new KomojuGateway();
        } else if(gatewayType == "paypal") {
            return new PayPalGateway();
        }
        throw invalid_argument("Gateway type not supported in US.");
    }

    Invoice* createInvoice() {
        return new GSTInvoice();
    }
    ~JapanFactory() {}
};





class CheckoutService {
    private:
    PaymentGateway* paymentGateway;
    Invoice* invoice;
    string gatewayType;

    public:
    CheckoutService(RegionFactory* regionFactory, string gatewayType): gatewayType(gatewayType) {
        this->paymentGateway = regionFactory->createPaymentGateway(gatewayType);
        this->invoice = regionFactory->createInvoice();
    }

    void completeOrder(double amount) {
        paymentGateway->processPayment(amount);
        invoice->generateInvoice();
    }
    ~CheckoutService() {}
};


int main() {
    CheckoutService *checkoutServiceIndia = new CheckoutService(new IndiaFactory(), "razorpay");
    CheckoutService *checkoutServiceUS = new CheckoutService(new USFactory(), "stripe");
    CheckoutService *checkoutServiceJapan = new CheckoutService(new JapanFactory(), "komojuGateway");
    return 0;
}



// class CheckoutService {
//     private:
//     string gatewayType, countryCode;

//     public:
//     CheckoutService(string gatewayType, string countryCode) : gatewayType(gatewayType), countryCode(countryCode) {}

//     void checkOut(double amount) {
//         /*
//         if(gatewayType == "razorpay") {
//             PaymentGateway* paymentGateway = new RazorpayGateway();
//             paymentGateway->processPayment(amount);
//         } else {
//             PaymentGateway* paymentGateway = new PayUGateway();
//             paymentGateway->processPayment(amount);
//         }

//         Invoice *invoice = new GSTInvoice();
//         invoice->generateInvoice();
//         */

//         /* if(countryCode == "INDIA") {
//             PaymentGateway* paymentGateway = IndiaFactory::createPaymentGateway(gatewayType);
//             paymentGateway->processPayment(amount);

//             Invoice *invoice = IndiaFactory::createInvoice();
//             invoice->generateInvoice();
//         } else {countryCode == "USA"} */
//     }
// };