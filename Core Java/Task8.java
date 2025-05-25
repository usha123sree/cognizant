package cognizant;

public class Task8 {

	    public static void main(String[] args) {
	        System.out.println("--- Operator Precedence Demonstration ---");

	        int result1 = 10 + 5 * 2;
	        System.out.println("Result of 10 + 5 * 2: " + result1);

	        int result2 = (10 + 5) * 2;
	        System.out.println("Result of (10 + 5) * 2: " + result2);

	        int result3 = 20 - 4 / 2 + 1;
	        System.out.println("Result of 20 - 4 / 2 + 1: " + result3);

	        double result4 = 15.0 / 4 + 3 * 2.5;
	        System.out.println("Result of 15.0 / 4 + 3 * 2.5: " + result4);

	        boolean result5 = (5 > 3 && 8 < 10) || (2 == 2 && 7 != 7);
	        System.out.println("Result of (5 > 3 && 8 < 10) || (2 == 2 && 7 != 7): " + result5);

	        int a = 10;
	        int b = 5;
	        int result6 = a++ * 2 + --b;
	        System.out.println("Result of a++ * 2 + --b (where a=10, b=5 initially): " + result6 + " (a is now " + a + ", b is now " + b + ")");
	    }
	}

