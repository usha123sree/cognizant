package cognizant;

import java.util.ArrayList;
import java.util.Scanner;

public class Task24 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();

        System.out.println("--- Student Name List Manager (ArrayList) ---");
        System.out.println("Enter student names (type 'done' to finish):");

        String name;
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();

            if (name.equalsIgnoreCase("done")) {
                break;
            }
            studentNames.add(name);
        }

        System.out.println("\n--- All Entered Student Names ---");
        if (studentNames.isEmpty()) {
            System.out.println("No names were entered.");
        } else {
            for (String studentName : studentNames) {
                System.out.println(studentName);
            }
        }

        scanner.close();
    }
}