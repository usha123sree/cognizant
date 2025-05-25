package cognizant;

class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class Task18 {
    public static void main(String[] args) {
        System.out.println("--- Inheritance Example ---");

        Animal myAnimal = new Animal();
        System.out.print("myAnimal: ");
        myAnimal.makeSound();

        Dog myDog = new Dog();
        System.out.print("myDog: ");
        myDog.makeSound();

        Animal anotherAnimal = new Dog();
        System.out.print("anotherAnimal (polymorphism): ");
        anotherAnimal.makeSound();
    }
}