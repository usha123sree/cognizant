package cognizant;

import java.util.concurrent.Callable;

public class MyCallableTask implements Callable<Integer> {

    private final int start;
    private final int end;
    private final String taskName;

    public MyCallableTask(String taskName, int start, int end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(taskName + " starting calculation from " + start + " to " + end + " on thread: " + Thread.currentThread().getName());
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
            // Simulate some work or delay
            Thread.sleep(10);
        }
        System.out.println(taskName + " finished. Result: " + sum);
        return sum;
    }
}