import java.util.*;

class Passport implements Cloneable {
    String passportNumber;

    Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
};

class Student implements Cloneable {
    String name;
    Passport passport;

    Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    // Shallow copy
    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    // return super.clone();
    // }

    // Deep copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.passport = (Passport) passport.clone();
        return student;
    }

};

public class Cloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        Passport passport = new Passport("123456789");
        Student student = new Student("Lakshay", passport);

        Student clonedStudent = (Student) student.clone();

        System.out.println("Before modifying the passport number:");
        System.out.print(student.name + " " + student.passport.passportNumber + "\n");
        System.out.print(clonedStudent.name + " " + clonedStudent.passport.passportNumber + "\n");

        // Modifying the passport number of the cloned student
        clonedStudent.name = "Rajat";
        clonedStudent.passport.passportNumber = "987654321";

        System.out.println("\nAfter modifying the passport number:");
        System.out.print(student.name + " " + student.passport.passportNumber + "\n");
        System.out.print(clonedStudent.name + " " + clonedStudent.passport.passportNumber + "\n");
    }
}