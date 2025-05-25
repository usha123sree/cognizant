package cognizant;

import java.util.Scanner;

public class Task14 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Array Sum and Average Calculator ---");

        int numElements;
        try {
            System.out.print("Enter the number of elements for the array: ");
            numElements = scanner.nextInt();

            if (numElements <= 0) {
                System.out.println("Number of elements must be positive.");
                scanner.close();
                return;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number for the number of elements.");
            scanner.close();
            return;
        }

        int[] numbers = new int[numElements];

        System.out.println("Enter " + numElements + " integer elements:");

        for (int i = 0; i < numElements; i++) {
            try {
                System.out.print("Enter element " + (i + 1) + ": ");
                numbers[i] = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer for the element.");
                scanner.close();
                return;
            }
        }

        long sum = 0;
        for (int i = 0; i < numElements; i++) {
            sum += numbers[i];
        }

        double average = (double) sum / numElements;

        System.out.println("\n--- Results ---");
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average of elements: " + average);

        scanner.close();
    }
}