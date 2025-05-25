package cognizant;

import java.util.Scanner;

public class Task15 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- String Reversal ---");
        System.out.print("Enter a string to reverse: ");
        String originalString = scanner.nextLine();

        // Using StringBuilder for efficient string reversal
        StringBuilder reversedStringBuilder = new StringBuilder(originalString);
        reversedStringBuilder.reverse();
        String reversedString = reversedStringBuilder.toString();

        System.out.println("Original string: " + originalString);
        System.out.println("Reversed string: " + reversedString);

        scanner.close();
    }
}
