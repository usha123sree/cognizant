package cognizant;

// Class extending Thread
class MyThread extends Thread {
    private String threadName;

    public MyThread(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " is running - Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
    }
}

// Class implementing Runnable
class MyRunnable implements Runnable {
    private String threadName;

    public MyRunnable(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(threadName + " is running - Count: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
    }
}

public class Task26 {

    public static void main(String[] args) {
        System.out.println("--- Thread Creation Example ---");

        MyThread thread1 = new MyThread("Thread-A");
        thread1.start();

        Thread thread2 = new Thread(new MyRunnable("Thread-B"));
        thread2.start();

        System.out.println("Main thread finished starting other threads.");
    }
}