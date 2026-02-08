import java.util.*;

// Abstract class
abstract class Car {
    abstract public void start();
    public void noise() {
        System.out.println("Vroom Vroom");
    }
};

interface CarInterface {
    public void start();

    default void noise() {
        System.out.println("Vroom Vroom from Interface");
    }

    static void numberOfWheels() {
        System.out.println("A car typically has 4 wheels.");
    }
};


class ManualCar implements CarInterface {
    public void start() {
        System.out.println("Manual Car starting...");
    }
};

class AutomaticCar extends Car {
    public void start() {
        System.out.println("Automatic Car is starting...");
    }
};

public class abstraction {
    public static void main(String[] args) {
        // Car manualCar = new ManualCar();
        // manualCar.start();
        // manualCar.noise();

        System.out.println();

        Car automaticCar = new AutomaticCar();
        automaticCar.start();
        automaticCar.noise();

        CarInterface.numberOfWheels();
    }
}