package cognizant;

interface Playable {
    void play();
}

class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Guitar strums a melody.");
    }
}

class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Piano plays harmonious chords.");
    }
}

public class Task19 {
    public static void main(String[] args) {
        System.out.println("--- Interface Implementation Example ---");

        Guitar myGuitar = new Guitar();
        System.out.print("My Guitar: ");
        myGuitar.play();

        Piano myPiano = new Piano();
        System.out.print("My Piano: ");
        myPiano.play();

        Playable instrument1 = new Guitar();
        System.out.print("Instrument 1 (Polymorphism): ");
        instrument1.play();

        Playable instrument2 = new Piano();
        System.out.print("Instrument 2 (Polymorphism): ");
        instrument2.play();
    }
}