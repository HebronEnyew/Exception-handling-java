/*Title: Exception Handling in a Student Grade System (Exceptions) 
Made by:
    Hebron Enyew   1506495
    Helen Adugna   1506501
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Custom exceptions for various error conditions
class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);
    }
}

class GradeAlreadySetException extends Exception {
    public GradeAlreadySetException(String message) {
        super(message);
    }
}

class NegativeGradeException extends Exception {
    public NegativeGradeException(String message) {
        super(message);
    }
}

class GradeFormatException extends Exception {
    public GradeFormatException(String message) {
        super(message);
    }
}

class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }
}

class GradeNotSetException extends Exception {
    public GradeNotSetException(String message) {
        super(message);
    }
}

// Student class to represent a student
class Student {
    private String name;
    private Integer grade; // Use Integer to allow null for uninitialized grade

    public Student(String name) {
        this.name = name;
        this.grade = null; // Initialize grade as null
    }

    // Method to set the student's grade with validation
    public void setGrade(int grade) throws InvalidGradeException, GradeAlreadySetException, NegativeGradeException {
        if (this.grade != null) {
            throw new GradeAlreadySetException("Grade has already been set for " + name + ".");
        }
        if (grade < 0) {
            throw new NegativeGradeException("Grade cannot be negative.");
        }
        if (grade > 100) {
            throw new InvalidGradeException("Grade " + grade + " is invalid. It must be between 0 and 100.");
        }
        this.grade = grade; // Set the grade
    }

    // Method to get the student's grade
    public int getGrade() throws GradeNotSetException {
        if (this.grade == null) {
            throw new GradeNotSetException("Grade has not been set for " + name + ".");
        }
        return this.grade; // Return the grade
    }

    public String getName() {
        return name; // Return the student's name
    }
}

// Main class to run the program
public class StudentGradeSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>(); // List to store students

    public static void main(String[] args) {
        while (true) {
            String studentName = getStudentName(); // Get student name
            try {
                checkDuplicateStudent(studentName); // Check for duplicates
                Student student = new Student(studentName); // Create new student
                setStudentGrade(student); // Set the student's grade
                students.add(student); // Add student to the list
                System.out.println("Student added: " + student.getName() + ", Grade: " + student.getGrade());
            } catch (DuplicateStudentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.print("Do you want to add another student? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break; // Exit the loop if the user doesn't want to continue
            }
        }
        scanner.close(); // Close the scanner resource
    }

    private static String getStudentName() {
        System.out.print("Enter the student's name: ");
        return scanner.nextLine(); // Return the entered name
    }

    private static void checkDuplicateStudent(String studentName) throws DuplicateStudentException {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                throw new DuplicateStudentException("A student with the name " + studentName + " already exists.");
            }
        }
    }

    private static void setStudentGrade(Student student) {
        while (true) {
            try {
                System.out.print("Enter the student's grade (0-100): ");
                String input = scanner.nextLine(); // Read input as a string
                if (!input.matches("\\d+")) { // Check if input is a valid integer
                    throw new GradeFormatException("Grade must be a whole number.");
                }
                int gradeInput = Integer.parseInt(input); // Convert to integer
                student.setGrade(gradeInput); // Set the grade
                break; // Exit the loop if the grade is set successfully
            } catch (InvalidGradeException | GradeAlreadySetException | NegativeGradeException | GradeFormatException e) {
                System.out.println("Error: " + e.getMessage()); // Print error message
            }
        }
    }
}