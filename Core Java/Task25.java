package cognizant;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task25 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> studentMap = new HashMap<>();

        System.out.println("--- Student ID to Name Mapper (HashMap) ---");
        System.out.println("Enter student IDs and names (type ID '0' to finish adding):");

        int id;
        String name;

        while (true) {
            try {
                System.out.print("Enter Student ID (integer, 0 to finish): ");
                id = scanner.nextInt();
                scanner.nextLine();

                if (id == 0) {
                    break;
                }

                System.out.print("Enter Student Name: ");
                name = scanner.nextLine();

                studentMap.put(id, name);
                System.out.println("Added: ID " + id + " -> " + name);

            } catch (InputMismatchException e) {
                System.out.println("Invalid input for ID. Please enter a whole number.");
                scanner.nextLine();
            }
        }

        System.out.println("\n--- Retrieve Student Name ---");
        if (studentMap.isEmpty()) {
            System.out.println("No student data available to retrieve.");
        } else {
            try {
                System.out.print("Enter the Student ID to retrieve their name: ");
                int searchId = scanner.nextInt();

                if (studentMap.containsKey(searchId)) {
                    String retrievedName = studentMap.get(searchId);
                    System.out.println("Student with ID " + searchId + " is: " + retrievedName);
                } else {
                    System.out.println("Student with ID " + searchId + " not found in the map.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for ID. Please enter a whole number.");
            }
        }

        scanner.close();
    }
}