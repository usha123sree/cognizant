package cognizant;
import java.util.Scanner;
public class Task5 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("--- Multiplication Table Generator ---");
	        int number;
	        try {
	            System.out.print("Enter a number to see its multiplication table: ");
	            number = scanner.nextInt();
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("Invalid input. Please enter an integer number.");
	            scanner.close();
	            return;
	        }
	        System.out.println("\nMultiplication Table for " + number + ":");

	        for (int i = 1; i <= 10; i++) {
	            int result = number * i;
	            System.out.println(number + " x " + i + " = " + result);
	        }
	        scanner.close();
	    }
	}
