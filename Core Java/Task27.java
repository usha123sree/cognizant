package cognizant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task27 {

    public static void main(String[] args) {
        System.out.println("--- Lambda Expressions Example (Sorting Strings) ---");

        List<String> names = new ArrayList<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("Bob");
        names.add("David");
        names.add("Eve");

        System.out.println("Original List: " + names);

        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

        System.out.println("Sorted List (Ascending): " + names);

        Collections.sort(names, (s1, s2) -> s2.compareTo(s1));

        System.out.println("Sorted List (Descending): " + names);
    }
}