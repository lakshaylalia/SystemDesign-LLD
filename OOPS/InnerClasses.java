import java.util.*;

class OuterClass {
    static int val = 10;
    String name = "OuterClass";

    // Static Inner Class
    /*
     * static class InnerClass {
     * public void execute() {
     * System.out.println("Static Inner Class accessing static member: " + val);
     * }
     * };
     * 
     * public void execute() {
     * InnerClass inner = new InnerClass();
     * inner.execute();
     * }
     */

    // Non-static Inner Class
    /*
     * class InnerClass {
     * public void execute() {
     * System.out.println("Non-Static Inner Class accessing static member: " + val);
     * }
     * };
     */

    // Local Inner Class
    public void execute() {
        final int x = 20;
        class InnerClass {
            public void execute() {
                System.out.println("Local Inner Class accessing static member: " + val);
                System.out.println("Local Inner Class accessing static member: " + x);
            }
        };
        // x += 10; // Error: cannot modify a final variable
        InnerClass inner = new InnerClass();
        inner.execute();
    }
};


// Anonymous Inner Class
abstract class A {
    abstract void func();
}

public class InnerClasses {
    public static void main(String[] args) {
        // OuterClass.InnerClass inner = new OuterClass.InnerClass();
        // inner.execute();
        // OuterClass outer = new OuterClass();
        // outer.execute();

        // OuterClass outer = new OuterClass();
        // OuterClass.InnerClass inner = outer.new InnerClass();
        // inner.execute();

        // OuterClass outer = new OuterClass();
        // outer.execute();

        A obj = new A() {
            @Override
            void func() {
                System.out.println("Anonymous Inner Class implementing abstract method");
            }
        };
        obj.func();


        ArrayList<Integer> list = new ArrayList<>() {
            @Override
            public boolean add(Integer e) {
                System.out.println("Adding element: " + e);
                return super.add(e);
            }
        };
        list.add(10);
        list.add(20);
        System.out.println("List: " + list);    
    }
}