package cognizant;

public class MyReflectableClass {

    private String name;
    private int id;

    public MyReflectableClass() {
        this("Default Name", 0);
    }

    public MyReflectableClass(String name, int id) {
        this.name = name;
        this.id = id;
        System.out.println("MyReflectableClass instance created: " + this.name);
    }

    public void publicMethodNoArgs() {
        System.out.println("publicMethodNoArgs called.");
    }

    public String publicMethodWithArgs(String prefix, int value) {
        String result = prefix + " " + this.name + " (" + value + ") " + this.id;
        System.out.println("publicMethodWithArgs called. Result: " + result);
        return result;
    }

    private int privateHelperMethod(int factor) {
        int calculatedValue = this.id * factor;
        System.out.println("privateHelperMethod called. Result: " + calculatedValue);
        return calculatedValue;
    }

    public static void staticMethod(String message) {
        System.out.println("Static method called with message: " + message);
    }
}