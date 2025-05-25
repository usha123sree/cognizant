package cognizant;

import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Task21 {

    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older to proceed.");
        } else {
            System.out.println("Age " + age + " is valid. You can proceed.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Custom Exception (InvalidAgeException) Example ---");
        System.out.print("Enter your age: ");

        int age = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                age = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for your age.");
                scanner.next();
                System.out.print("Enter your age: ");
            }
        }

        try {
            checkAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}