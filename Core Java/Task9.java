package cognizant;

import java.util.Scanner;
public class Task9 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("--- Grade Calculator ---");
	        System.out.print("Enter marks (out of 100): ");
	        int marks;
	        try {
	            marks = scanner.nextInt();
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a whole number for marks.");
	            scanner.close();
	            return;
	        }
	        if (marks < 0 || marks > 100) {
	            System.out.println("Invalid marks. Please enter a value between 0 and 100.");
	        } else if (marks >= 90) {
	            System.out.println("Grade: A");
	        } else if (marks >= 80) {
	            System.out.println("Grade: B");
	        } else if (marks >= 70) {
	            System.out.println("Grade: C");
	        } else if (marks >= 60) {
	            System.out.println("Grade: D");
	        } else {
	            System.out.println("Grade: F");
	        }
	        scanner.close();
	    }
	}