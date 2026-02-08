import java.util.*;

class School {
    private String name;
    public School() {
        name = "DPS";
    }

    public void printSchoolName() {
        System.out.println("School Name: " + name);
    }
};

class Student extends School {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void printStudentName() {
        System.out.println("Student Name: " + name);
    }
};

class Parent extends Student {
    private String name;

    public Parent(String name, String studentName) {
        super(studentName);
        this.name = name;
    }

    public void printParentName() {
        System.out.println("Parent Name: " + name);
    }
};

class Teacher extends School {
    private String name;
    private String subjectName;

    Teacher(String name, String subjectName) {
        this.name = name;
        this.subjectName = subjectName;
    }

    public void printTeacherDetails() {
        System.out.println("Teacher Name: " + name);
        System.out.println("Subject Name: " + subjectName);
    }
}

public class inheritance {
    public static void main(String[] args) {
       Student student = new Student("Alice");
       Teacher teacher = new Teacher("Mr. Smith", "Mathematics");
       teacher.printSchoolName();
       student.printStudentName();
       teacher.printTeacherDetails();
    }
}
