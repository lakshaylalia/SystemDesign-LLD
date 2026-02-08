import java.util.*;

/* class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void display(String indent) {
        System.out.println(indent + "Product: " + name + " - $" + price);
    }
};

class ProductBundle {
    private String bundleName;
    private List<Product> products = new ArrayList<>();

    public ProductBundle(String bundleName) {
        this.bundleName = bundleName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void display(String indent) {
        System.out.println(indent + "Bundle: " + bundleName);
        for (Product product : products) {
            product.display(indent + "  ");
        }
    }
}; */


interface Component
{
    public void showPrice();
};


class Leaf implements Component
{
    String name;
    Double price;
    public Leaf()
    {

    }
    public Leaf(String name,double price)
    {
        this.name = name;
        this.price = price;
    }

    @Override
    public void showPrice() {
        System.out.println(this.price);
    }
};


class CompositeC implements Component {
    String name;

    ArrayList<Component> components;

    public CompositeC()
    {

    }

    public CompositeC(String name)
    {
        this.name = name;
        components = new ArrayList<>();
    }

    @Override
    public void showPrice()
    {
        for (Component c : components)
        {
            c.showPrice();
        }
    }

    public void add(Component subComponent)
    {
        components.add(subComponent);
    }

};



public class Composite {
    public static void main(String[] args) {
        /* Product book = new Product("Book", 500);
        Product headphones = new Product("Headphones", 1500);
        Product charger = new Product("Charger", 800);
        Product pen = new Product("Pen", 20);
        Product notebook = new Product("Notebook", 60);
        Product phone = new Product("Phone", 10000);

        // Bundle: Iphone Combo
        ProductBundle iphoneCombo = new ProductBundle("iPhone Combo Pack");
        iphoneCombo.addProduct(phone);
        iphoneCombo.addProduct(headphones);
        iphoneCombo.addProduct(charger);

        // Bundle: School Kit
        ProductBundle schoolKit = new ProductBundle("School Kit");
        schoolKit.addProduct(pen);
        schoolKit.addProduct(notebook);

        // Add to cart logic
        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        // Display Cart
        double total = 0;
        System.out.println("Cart Details:\n");

        for (Object item : cart) {
            if (item instanceof Product) {
                ((Product) item).display("  ");
                total += ((Product) item).getPrice();
            } else if (item instanceof ProductBundle) {
                ((ProductBundle) item).display("  ");
                total += ((ProductBundle) item).getPrice();
            }
        }

        System.out.println("\nTotal Price: ₹" + total); */

        Component hdd       = new Leaf("hdd" , 4000);
        Component keyboard  = new Leaf("keyboard",1000);
        Component mouse     = new Leaf("mouse",500);
        Component ram       = new Leaf("ram",3000);
        Component processor = new Leaf("Processor",10000);


        CompositeC computer = new CompositeC("computer");

        CompositeC motherboard = new CompositeC("motherboard");
        motherboard.add(ram);
        motherboard.add(processor);


        CompositeC cabinet  = new CompositeC("cabinet");
        cabinet.add(hdd);
        cabinet.add(motherboard);

        CompositeC peripherals     = new CompositeC("peripherals");
        peripherals.add(keyboard);
        peripherals.add(mouse);

        computer.add(cabinet);
        computer.add(peripherals);

        computer.showPrice();
    }
};