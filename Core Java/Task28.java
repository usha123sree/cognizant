package cognizant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task28 {

    public static void main(String[] args) {
        System.out.println("--- Stream API Example: Filtering Even Numbers ---");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original List of Numbers: " + numbers);

        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0)
                                           .collect(Collectors.toList());

        System.out.println("Even Numbers (filtered using Stream API): " + evenNumbers);
    }
}