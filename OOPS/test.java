import java.util.*;

class Employee {
    private String name;
    private double salary;

    public Employee() {
        name = "";
        salary = 0.0;
    }

    public Employee(String name) {
        this.name = name;
        this.salary = 0.0;
    }

    public Employee(double salary) {
        this.name = "";
        this.salary = salary;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

     public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Employee(Employee employee) {
        this(employee.getName(), employee.getSalary());
        // this(employee.name, employee.salary);
    }



};

class BankAccount {
    private String name;
    private double balance;

    BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    BankAccount() {
        name = "";
        balance = 0.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        System.out.println("Insufficient funds");
        return false;
    }
}

public class test {
    public static void main(String[] args) {

    }
}
