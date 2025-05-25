package cognizant;

public class Task7 {

	    public static void main(String[] args) {
	        System.out.println("--- Type Casting Demonstration ---");

	        double originalDouble = 98.765;
	        System.out.println("Original double value: " + originalDouble);

	        int castedInt = (int) originalDouble;
	        System.out.println("Double casted to int (truncates decimal): " + castedInt);

	        int originalInt = 123;
	        System.out.println("\nOriginal int value: " + originalInt);

	        double castedDouble = (double) originalInt;
	        System.out.println("Int casted to double (adds decimal): " + castedDouble);
	        int anotherInt = 7;
	        double anotherDouble = (double) anotherInt;
	        System.out.println("\nAnother int: " + anotherInt);
	        System.out.println("Another int casted to double: " + anotherDouble);
	        double preciseDouble = 5.99;
	        int convertedInt = (int) preciseDouble;
	        System.out.println("\nPrecise double: " + preciseDouble);
	        System.out.println("Precise double casted to int: " + convertedInt);
	    }
	}
