package cognizant;
import java.util.Scanner;
public class Task11 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("--- Factorial Calculator ---");
	        System.out.print("Enter a non-negative integer: ");
	        int number;
	        try {
	            number = scanner.nextInt();
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a whole number.");
	            scanner.close();
	            return;
	        }
	        if (number < 0) {
	            System.out.println("Factorial is not defined for negative numbers.");
	        } else if (number == 0) {
	            System.out.println("The factorial of 0 is 1.");
	        } else {
	            long factorial = 1;

	            for (int i = 1; i <= number; i++) {
	                factorial *= i;
	            }
	            System.out.println("The factorial of " + number + " is " + factorial + ".");
	        }
	        scanner.close();
	    }
	}