import java.util.*;

// Generic class
class ExampleGenerics<T> {
    private List<T> list = new ArrayList<>();

    public void add(T val) { // Generic method to add an element
        list.add(val);
    }

    public void removeLast() {
        if (list.isEmpty()) {
            System.out.println("List is empty, cannot remove element.");
        }
        list.remove(list.size() - 1);
    }

    public T getElement(int index) {
        return list.get(index);
    }

    public void print(){
        for(T el : list){
            System.out.println(el);
        }
    }
};

class Calculator <T extends Number> {
    public double add(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    public double subtract(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    public double multiply(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    public double divide(T a, T b) {
        if (b.doubleValue() == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a.doubleValue() / b.doubleValue();
    }
}

public class Generics {

    public static <T> void printArray(List<? extends T> ls) { // Generic method to print an array
        for (T element : ls) {
            System.out.println(element);
        }
    }
    public static <T> void writeArray(List<? super Integer> ls) { // Generic method to print an array
        ls.add(10);
    }
    public static void main(String[] args) {
        List<Integer> ls = new ArrayList<>();
        ls.add(10);
        ls.add(20);
        ls.add(30);
        printArray(ls);
    }
}
