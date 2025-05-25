package cognizant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Division with Try-Catch Example ---");

        int numerator = 0;
        int denominator = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter the numerator (first number): ");
                numerator = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for the numerator.");
                scanner.next();
            }
        }

        validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter the denominator (second number): ");
                denominator = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number for the denominator.");
                scanner.next();
            }
        }

        try {
            int result = numerator / denominator;
            System.out.println("Result of division: " + numerator + " / " + denominator + " = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero. Please ensure the denominator is not zero.");
        } finally {
            scanner.close();
        }
    }
}