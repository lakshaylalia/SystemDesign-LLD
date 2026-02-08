import java.util.*;

// Don't have any constructor
interface CarInterface {
    static final int cnt = 0; // its a public static final variable
    public void start();
    public void numberOfGears();
    // default method
    default void airBags() {
        System.out.println("Car has 5 airbags");
    }

    // static method
    static void honk() {
        System.out.println("Car is Honking");
    }
};

interface Sunroof {
    public void sunRoof();
}

class ManualCar implements CarInterface, Sunroof {
    public void start() {
        // cnt++; //  we cannot change value of  cnt here
        System.out.println("Manual Car is starting...");
    }

    public void numberOfGears() {
        System.out.println("Manual Car has 6 gears");
    }

    @Override
    public void airBags() {
        System.out.println("Manual Car has 6 airbags");
    }

    public void sunRoof() {
        System.out.println("Manual Car has  normal sunroof");
    }

};


/*
interface Vehicle {
    public void start()
} 

interface Car extends Vehcicle {
    public void numberOfGears()
} 
class ManualCar implements Car {
    public void start() {
        System.out.println("Manual Car is starting...");
    }

    public void numberOfGears() {
        System.out.println("Manual Car has 6 gears");
    }
};   
*/



public class Interfaces {
    public static void main(String args[]) {
        CarInterface manualCar = new ManualCar();
        manualCar.start();
        manualCar.numberOfGears();
        manualCar.airBags();
        // manualCar.sunRoof(); // This will cause a compile-time error, we have to make a manualCar of type Sunroof to call this method
        // manualCar.honk(); // This will cause a compile-time error

        CarInterface.honk(); // Correct way to call static method
    }
};