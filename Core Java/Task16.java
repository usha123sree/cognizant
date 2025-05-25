package cognizant;

import java.util.Scanner;

public class Task16 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Palindrome Checker ---");
        System.out.print("Enter a string to check if it's a palindrome: ");
        String originalString = scanner.nextLine();

        String cleanedString = originalString.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        StringBuilder reversedStringBuilder = new StringBuilder(cleanedString);
        reversedStringBuilder.reverse();
        String reversedString = reversedStringBuilder.toString();

        System.out.println("Original string: \"" + originalString + "\"");
   
        if (cleanedString.equals(reversedString)) {
            System.out.println("Result: It IS a palindrome!");
        } else {
            System.out.println("Result: It is NOT a palindrome.");
        }

        scanner.close();
    }
}