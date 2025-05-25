package cognizant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Task41 {

    private static final int NUMBER_OF_TASKS = 5;
    private static final int THREAD_POOL_SIZE = 2; // Number of threads in the pool

    public static void main(String[] args) {
        System.out.println("ExecutorService and Callable Demonstration");
        System.out.println("----------------------------------------");

        // Step 1: Create an ExecutorService with a fixed thread pool
        // This pool will reuse a fixed number of threads for all submitted tasks.
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        System.out.println("ExecutorService created with " + THREAD_POOL_SIZE + " threads.");

        // List to hold the Future objects, which will contain the results
        List<Future<Integer>> results = new ArrayList<>();

        // Step 2: Create and submit Callable tasks
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            int startRange = i * 10 + 1;
            int endRange = (i + 1) * 10;
            Callable<Integer> task = new MyCallableTask("Task-" + (i + 1), startRange, endRange);
            Future<Integer> future = executor.submit(task); // Submit the task and get a Future
            results.add(future);
        }

        System.out.println("\nAll tasks submitted. Collecting results...");

        // Step 3: Collect results using Future.get()
        int totalSum = 0;
        for (int i = 0; i < results.size(); i++) {
            Future<Integer> future = results.get(i);
            try {
                // Future.get() blocks until the task completes and returns its result
                Integer taskResult = future.get();
                System.out.println("Result of Task-" + (i + 1) + ": " + taskResult);
                totalSum += taskResult;
            } catch (InterruptedException e) {
                // This exception is thrown if the current thread was interrupted
                System.err.println("Task-" + (i + 1) + " was interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Re-interrupt the current thread
            } catch (ExecutionException e) {
                // This exception wraps any exception thrown by the Callable's call() method
                System.err.println("Task-" + (i + 1) + " threw an exception: " + e.getCause().getMessage());
            }
        }

        System.out.println("\nAll task results collected. Total Sum: " + totalSum);

        // Step 4: Shut down the ExecutorService
        // This prevents the executor from accepting new tasks but finishes existing ones.
        executor.shutdown();
        System.out.println("\nExecutorService shutdown initiated.");

        try {
            // Wait for all tasks to complete or for a timeout
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("ExecutorService did not terminate in the specified time.");
                executor.shutdownNow(); // Force shutdown if tasks are still running
            }
        } catch (InterruptedException e) {
            System.err.println("ExecutorService termination was interrupted: " + e.getMessage());
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("ExecutorService terminated.");
    }
}
