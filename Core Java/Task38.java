package cognizant;

public class Task38 {

    private String name;
    private int value;

    public Task38(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void processValue() {
        if (value > 10) {
            System.out.println(name + " is greater than 10.");
            for (int i = 0; i < 3; i++) {
                System.out.println("Processing step " + (i + 1));
            }
        } else {
            System.out.println(name + " is 10 or less.");
        }
    }

    public static void main(String[] args) {
        Task38 obj1 = new Task38("Example1", 15);
        obj1.processValue();

        Task38 obj2 = new Task38("Example2", 8);
        obj2.processValue();
    }
}