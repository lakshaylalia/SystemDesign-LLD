import java.util.*;

/* class PlainPizza {};
class OlivesPizza extends PlainPizza {};
class StuffedPizza extends PlainPizza {};
class CheesePizza extends PlainPizza {};
class CheeseStuffedPizza extends CheesePizza {};
class CheeseOlivesPizza extends CheesePizza {};
class CheeseOliveStuffedPizza extends CheesePizza {}; */

interface Pizza {
    String getDescription();

    double getCost();
};

// Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
};

class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
};

// Decorator Abstract Class
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // HAS-A relationship with Pizza

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

// Concrete Decorator
class ExtraCheese extends PizzaDecorator {
    public ExtraCheese(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
};

class Olive extends PizzaDecorator {
    public Olive(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 60.0;
    }
};

class StuffedCrust extends PizzaDecorator {
    public StuffedCrust(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + " + Stuffed Crust";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 80.0;
    }
};

public class Decorator {
    public static void main(String[] args) {
        Pizza pizza = new StuffedCrust(new ExtraCheese(new Olive(new MargheritaPizza())));
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: " + pizza.getCost());
    }
};