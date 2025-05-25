package cognizant;
import java.util.Scanner;
public class Task2 {
	    public static void main(String[] args) {
	        Scanner input = new Scanner(System.in); 
	        System.out.println("Welcome to the Simple Calculator!");
	        double num1, num2;
	        try {
	            System.out.print("Enter the first number: ");
	            num1 = input.nextDouble();

	            System.out.print("Enter the second number: ");
	            num2 = input.nextDouble(); 
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("Invalid input. Please enter numeric values for the numbers.");
	            input.close();
	            return; 
	        }

	        System.out.println("\nChoose an operation:");
	        System.out.println("1. Addition (+)");
	        System.out.println("2. Subtraction (-)");
	        System.out.println("3. Multiplication (*)");
	        System.out.println("4. Division (/)");

	        System.out.print("Enter the number corresponding to the desired operation (1/2/3/4): ");
	        String operation = input.next();

	        double result; 

	        switch (operation) {
	            case "1":
	                result = num1 + num2;
	                System.out.println("The result of addition is: " + num1 + " + " + num2 + " = " + result);
	                break; 
	            case "2":
	                result = num1 - num2;
	                System.out.println("The result of subtraction is: " + num1 + " - " + num2 + " = " + result);
	                break;
	            case "3":
	                result = num1 * num2;
	                System.out.println("The result of multiplication is: " + num1 + " * " + num2 + " = " + result);
	                break;
	            case "4":
	                if (num2 != 0) { 
	                    result = num1 / num2;
	                    System.out.println("The result of division is: " + num1 + " / " + num2 + " = " + result);
	                } else {
	                    System.out.println("Error: Division by zero is not allowed.");
	                }
	                break;
	            default: 
	                System.out.println("Invalid operation choice. Please choose a number between 1 and 4.");
	                break;
	        }

	        input.close(); 
	    }
	}
