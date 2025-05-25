package cognizant;

public class Task30 {

    public static void processObject(Object obj) {
        String message = switch (obj) {
            case Integer i -> "It's an Integer with value: " + i;
            case String s -> "It's a String with value: \"" + s + "\"";
            case Double d -> "It's a Double with value: " + d;
            case Boolean b -> "It's a Boolean with value: " + b;
            default -> "It's an unknown type: " + obj.getClass().getName();
        };
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println("--- Pattern Matching for Switch (Java 21+) ---");

        processObject(10);
        processObject("Hello Java 21");
        processObject(3.14159);
        processObject(true);
        processObject(new int[]{1, 2, 3});
        processObject(null);
    }
}