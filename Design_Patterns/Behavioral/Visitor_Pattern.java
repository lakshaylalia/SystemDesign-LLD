import java.util.*;

/*
class PhysicalProduct {
    void printInvoice() {}
    double calculateShippingCost() {
        return 10.0; // Flat shipping cost for physical products
    }
};


class DigitalProduct {
    void printInvoice() {}
    // No shipping cost for digital products
};

class GiftCard {
    void printInvoice() {}
    double calculateDiscount() {
        return 5.0; // Flat discount for gift cards
    }
};
*/

// Element Interface
interface Item {
    void accept(ItemVisitor visitor);
};

// ======= Concrete elements ===========
class PhysicalProduct implements Item {
    String name;
    double weight;

    public PhysicalProduct(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
};

// ======= Concrete elements ===========
class DigitalProduct implements Item {
    String name;
    int downloadSizeInMB;

    public DigitalProduct(String name, int downloadSizeInMB) {
        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
};

// ======= Concrete elements ===========
class GiftCard implements Item {
    String code;
    double amount;

    public GiftCard(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
};

// ======== Visitor Interface ============
interface ItemVisitor {
    void visit(PhysicalProduct item);
    void visit(DigitalProduct item);
    void visit(GiftCard item);
}


// ============ Concrete Visitors ==============
class InvoiceVisitor implements ItemVisitor {
    public void visit(PhysicalProduct item) {
        System.out.println("Invoice: " + item.name + " - Shipping to customer");

    }

    public void visit(DigitalProduct item) {
        System.out.println("Invoice: " + item.name + " - Email with download link");
    }

    public void visit(GiftCard item) {
        System.out.println("Invoice: Gift Card - Code: " + item.code);
    }
}

// ============ Concrete Visitors ==============
class ShippingCostVisitor implements ItemVisitor {
    public void visit(PhysicalProduct item) {
        System.out.println("Shipping cost for " + item.name + ": Rs. " + (item.weight * 10));


    }

    public void visit(DigitalProduct item) {
        System.out.println(item.name + " is digital -- No shipping cost.");
    }

    public void visit(GiftCard item) {
        System.out.println("GiftCard delivery via email -- No shipping cost.");
    }
}

class WareHouseCostVisitor implements ItemVisitor {
    public void visit(PhysicalProduct item) {
        System.out.println("WareHouse cost for " + item.name + ": Rs. " + (item.weight * 5));
    }

    public void visit(DigitalProduct item) {
        System.out.println(item.name + " is digital -- No wareHouse cost.");
    }

    public void visit(GiftCard item) {
        System.out.println("GiftCard delivery via email -- No wareHouse cost.");
    }
}


public class Visitor_Pattern {
    public static void main(String agrds[]) {
        List<Item> items = new ArrayList<>();
        items.add(new PhysicalProduct("Laptop", 2.5));
        items.add(new DigitalProduct("E-book", 500));
        items.add(new GiftCard("GIFT123", 50.0));

        ItemVisitor invoiceGenerator = new InvoiceVisitor();
        ItemVisitor shippingCalculator = new ShippingCostVisitor();

        for(Item item : items) {
            item.accept(invoiceGenerator);
            item.accept(shippingCalculator);
        }
    }
}
