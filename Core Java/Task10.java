package cognizant;
import java.util.Random;
import java.util.Scanner;
public class Task10 {
	 public static void main(String[] args) {
	       Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        int randomNumber = random.nextInt(100) + 1;
	        int guess;
	        int attempts = 0;
	        boolean hasGuessedCorrectly = false;
	        System.out.println("--- Welcome to the Number Guessing Game! ---");
	        System.out.println("I have generated a number between 1 and 100. Try to guess it!");
	        while (!hasGuessedCorrectly) {
	            System.out.print("Enter your guess: ");
	            attempts++;
	            try {
	                guess = scanner.nextInt();

	                if (guess < 1 || guess > 100) {
	                    System.out.println("Your guess is out of range. Please guess a number between 1 and 100.");
	                } else if (guess < randomNumber) {
	                    System.out.println("Too low! Try again.");
	                } else if (guess > randomNumber) {
	                    System.out.println("Too high! Try again.");
	                } else {
	                    hasGuessedCorrectly = true;
	                    System.out.println("Congratulations! You guessed the number " + randomNumber + " correctly!");
	                    System.out.println("It took you " + attempts + " attempts.");
	                }
	            } catch (java.util.InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a whole number.");
	                scanner.next();
	            }
	        }
	        scanner.close();
	    }
	}