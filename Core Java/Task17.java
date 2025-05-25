package cognizant;

class Car {
    String make;
    String model;
    int year;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayDetails() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("--------------------");
    }
}

public class Task17 {

    public static void main(String[] args) {
        System.out.println("--- Car Class and Object Demonstration ---");

        Car car1 = new Car("Toyota", "Camry", 2022);
        System.out.println("Details for Car 1:");
        car1.displayDetails();

        Car car2 = new Car("Honda", "Civic", 2023);
        System.out.println("Details for Car 2:");
        car2.displayDetails();

        Car car3 = new Car("Ford", "Mustang", 2024);
        System.out.println("Details for Car 3:");
        car3.displayDetails();
    }
}