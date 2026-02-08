
class BankAccount {
    private static int counter = 0;
    String name;
    double balance;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public String getName() {
        return name;
    } 
};

public class Static {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("John", 1000.0); 
        BankAccount account2 = new BankAccount("Jane", 1500.0);
        System.out.println(BankAccount.getCounter());
    }
}
