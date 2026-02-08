/*
Datatypes:
Numbers
1. byte: Integers from -128 to 127, takes 8 bits
    byte a = -50;
2. short: Integers from -32,768 to 32,767, takes 16 bits
    short b = 1000;
3. int: Integers from -2,147,483,648 to 2,147,483,647, takes 32 bits
    int c = 1000000;
4. long: Integers from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807, takes 64 bits
    long d = 1000000000000L;
5. float: Floating point numbers, takes 32 bits
    float e = 10.5f;
6. double: Floating point numbers, takes 64 bits
    double f = 10.5;

Characters
1. char: Characters, takes 16 bits
    char a = 'A';

Booleans:
1. boolean: True or False, takes 1 bit
    boolean a = true/false;

Strings:
Strings are immutable, cannot be changed once created, if mutatted, a new string is created and stored at new location in memory
1. String: Text, takes 16 bits
    String s = "Hello!"; 
    System.out.println(name.length());
    System.out.println(s.charAt(0));
    System.out.println(name.substring(0));
    System.out.println(name.substring(0, 2)); // end index is not included
    System.out.println(name.subSequence(0, 2));

Input:
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();
    double num = scanner.nextDouble();
    String s = scanner.nextLine(); // reads the entire line
    String first = scanner.next(); // reads the first word
    String last = scanner.next();
    System.out.println(first + " " + last);
    scanner.close();

TypeCasting:
    1. Implicit: Automatic conversion of data types from smaller to larger data types
    2. Explicit: Manual conversion of data types from larger to smaller data types

Constants:
    final int a = 10;
    final keyword is used to declare a constant

Arrays:
    int[] arr = new int[5];
    int[] arr = {1, 2, 3, 4, 5};
    int[][] arr = {{1}, {2, 3}, {4, 5, 6}};
    System.out.println(arr.length); // length of the array
    for (int[] i : arr) {
        for (int j : i) {
            System.out.println(j);
            }
        }

Exception Handling:
    int[] arr = {1, 2, 3, 4};
    try {
        // code that may throw an exception
        System.out.println(arr[5]);
    } catch (Exception e) {
        // code to handle the exception
        System.out.println("An error occurred: " + e.getMessage());
    } finally {
        // code that will always be executed
        System.out.println("Execution completed.");
    }
*/

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
         int[] arr = {1, 2, 3, 4};
        try {
            // code that may throw an exception
            System.out.println(arr[5]);
            int x = 10;
        } catch (Exception e) {
            // code to handle the exception
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // code that will always be executed
            System.out.println("Execution completed.");
        }
    }
}
