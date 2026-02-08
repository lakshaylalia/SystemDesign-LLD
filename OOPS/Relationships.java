import java.util.*;


// 1. Association Relationship
// One-to-One Relationship Example : A student has only one Passport and a Passport is assigned to only one student
/* class Passport {
    private String passportNumber;

    Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
};

class Student {
    private String name;
    private Passport passport;

    Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Passport Number: " + passport.getPassportNumber());
    }
} */

// One-to-Many Relationship Example : A college has many students but a student belongs to only one college
/* class College {
    private String name;
    private List<Student> students;

    College(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printAllStudents() {
        for (Student student : students) {
            student.displayDetails();
        }
    }
}

class Student {
    private String name;
    private String id;

    Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void displayDetails() {
        System.out.println("Name: " + name + ", ID: " + id);
    }

} */

// Many-to-Many Relationship Example : A course can have many students and a student can enroll in many courses
/* class Course {
    private String name;
    private List<Student> students;

    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getCourseName() {
        return name;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println("Student Name: " + student.getStudentName());
        }
    }
}

class Student {
    private String name;
    private List<Course> courses;

    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getStudentName() {
        return name;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayAllCourses() {
        for (Course course : courses) {
            System.out.println("Course Name: " + course.getCourseName());
        }
    }
} */



// 2. Aggregation Relationship

class Passport {
    private String passportNumber;

    Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
};

class Student {
    private String name;
    private Passport passport;
    Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Passport Number: " + passport.getPassportNumber());
    }
};

public class Relationships {
    public static void main(String[] args) {
        // Student student1 = new Student("Alice", "S001");
        // Student student2 = new Student("Bob", "S002");
        // College college = new College("ABC College");
        // college.addStudent(student1);
        // college.addStudent(student2);
        // college.printAllStudents();

        // Course course1 = new Course("Mathematics");
        // Course course2 = new Course("Physics");

        // Student student1 = new Student("Alice");
        // Student student2 = new Student("Bob");

        // course1.addStudent(student1);
        // student1.addCourse(course1);
        // student1.displayAllCourses();
        // course1.displayAllStudents();

        // course2.addStudent(student2);
        // student2.addCourse(course2); 
        // student2.displayAllCourses();
        // course2.displayAllStudents();
    }
}
