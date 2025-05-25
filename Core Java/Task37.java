package cognizant;

public class Task37 {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int sum = addNumbers(a, b);
        System.out.println("The sum is: " + sum);

        String message = "Hello Bytecode";
        printMessage(message);
    }

    public static int addNumbers(int x, int y) {
        int result = x + y;
        return result;
    }

    public static void printMessage(String msg) {
        System.out.println("Message: " + msg);
    }
}