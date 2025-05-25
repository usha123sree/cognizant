package cognizant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task22 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- File Writing Example ---");
        System.out.print("Enter a string to write to output.txt: ");
        String userInput = scanner.nextLine();

        String fileName = "output.txt";

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(userInput);
            writer.close();
            System.out.println("Successfully wrote \"" + userInput + "\" to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}