package cognizant;

import java.util.Scanner;

public class Task13 {
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci is not defined for negative numbers.");
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Recursive Fibonacci Calculator ---");
        System.out.print("Enter a non-negative integer (n) to find the nth Fibonacci number: ");

        int n;
        try {
            n = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            scanner.close();
            return;
        }

        try {
            long result = fibonacci(n);
            System.out.println("The " + n + "th Fibonacci number is: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

