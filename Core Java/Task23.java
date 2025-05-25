package cognizant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task23 {

    public static void main(String[] args) {
        String fileName = "output.txt";

        System.out.println("--- File Reading Example ---");
        System.out.println("Attempting to read from " + fileName + ":");

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
            System.out.println("\nSuccessfully read content from " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            System.out.println("Please ensure '" + fileName + "' exists in the same directory as the program.");
        }
    }
}