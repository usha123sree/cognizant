package cognizant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

record Person(String name, int age) {
}

public class Task29 {

    public static void main(String[] args) {
        System.out.println("--- Records Example (Java 16+) ---");

        // Create instances of the Person record
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);
        Person person3 = new Person("Charlie", 35);
        Person person4 = new Person("David", 28);
        Person person5 = new Person("Eve", 40);

        // Print instances (demonstrates default toString(), equals(), hashCode())
        System.out.println("Individual Person Records:");
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);

        // Use records in a List
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);

        System.out.println("\nAll People in List: " + people);

        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 30)
                                    .collect(Collectors.toList());

        System.out.println("People aged 30 or older: " + adults);

        System.out.println("\nAccessing components:");
        System.out.println("Name of person1: " + person1.name());
        System.out.println("Age of person1: " + person1.age());
    }
}