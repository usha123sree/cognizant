package cognizant;

public class Task12 {
	    public int add(int a, int b) {
	        return a + b;
	    }
	    public double add(double a, double b) {
	        return a + b;
	    }
	    public int add(int a, int b, int c) {
	        return a + b + c;
	    }

	    public static void main(String[] args) {
	        Task12 calculator = new Task12(); // Create an instance of the class

	        System.out.println("--- Method Overloading Demonstration ---");

	        // Call the add method that accepts two integers
	        int sum1 = calculator.add(5, 10);
	        System.out.println("Sum of two integers (5, 10): " + sum1);

	        // Call the add method that accepts two doubles
	        double sum2 = calculator.add(3.5, 2.7);
	        System.out.println("Sum of two doubles (3.5, 2.7): " + sum2);

	        // Call the add method that accepts three integers
	        int sum3 = calculator.add(1, 2, 3);
	        System.out.println("Sum of three integers (1, 2, 3): " + sum3);

	        // Another example for clarity
	        double sum4 = calculator.add(10.5, 20.0);
	        System.out.println("Sum of two doubles (10.5, 20.0): " + sum4);

	        int sum5 = calculator.add(100, 200, 300);
	        System.out.println("Sum of three integers (100, 200, 300): " + sum5);
	    }
	}
