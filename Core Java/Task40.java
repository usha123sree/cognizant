package cognizant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Task40 {

    private static final int NUM_VIRTUAL_THREADS = 100_000;
    // NOTE: Attempting to launch 100,000 platform threads will almost certainly
    // result in an OutOfMemoryError or severe system instability on typical machines.
    // For a meaningful comparison that can actually run, we'll use a smaller number
    // for platform threads to show their performance characteristics.
    private static final int NUM_PLATFORM_THREADS_FOR_DEMO = 5_000; // A more feasible number for platform threads

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Java Virtual Threads (JEP 444) Demonstration - Requires JDK 21+");
        System.out.println("------------------------------------------------------------------");

        // --- Virtual Threads Demonstration ---
        System.out.println("Launching " + NUM_VIRTUAL_THREADS + " virtual threads...");
        long startTimeVirtual = System.nanoTime();
        CountDownLatch latchVirtual = new CountDownLatch(NUM_VIRTUAL_THREADS);

        // Using ExecutorService.newVirtualThreadPerTaskExecutor() is the idiomatic way
        // to manage many virtual threads, where each submitted task runs in its own virtual thread.
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM_VIRTUAL_THREADS; i++) {
                final int threadId = i;
                executor.submit(() -> {
                    // Simulate a small amount of non-blocking work
                    // System.out.println("Virtual Thread: " + threadId); // Uncomment to see output, but it's very noisy
                    latchVirtual.countDown(); // Signal that this thread has completed its task
                });
            }
        } // The 'try-with-resources' block ensures the executor is shut down and awaited

        latchVirtual.await(); // Wait for all virtual threads to complete
        long endTimeVirtual = System.nanoTime();
        long durationVirtualMillis = TimeUnit.NANOSECONDS.toMillis(endTimeVirtual - startTimeVirtual);
        System.out.println("Finished launching and running " + NUM_VIRTUAL_THREADS + " virtual threads in " + durationVirtualMillis + " ms");

        System.out.println("\n------------------------------------------------------------------");

        // --- Platform Threads Demonstration ---
        System.out.println("Attempting to launch a large number of platform threads for comparison.");
        System.out.println("WARNING: Launching " + NUM_VIRTUAL_THREADS + " platform threads is generally NOT feasible and can lead to system instability or OutOfMemoryError.");
        System.out.println("For a practical demonstration, we will launch " + NUM_PLATFORM_THREADS_FOR_DEMO + " platform threads.");

        long startTimePlatform = System.nanoTime();
        CountDownLatch latchPlatform = new CountDownLatch(NUM_PLATFORM_THREADS_FOR_DEMO);

        // We will directly create Thread objects using Thread.ofPlatform() to demonstrate
        // launching distinct OS-level threads, similar to how virtual threads are launched.
        // Managing this many platform threads via ExecutorService with a fixed pool might
        // hide the true cost of launching many *actual* threads.
        Thread[] platformThreads = new Thread[NUM_PLATFORM_THREADS_FOR_DEMO];

        for (int i = 0; i < NUM_PLATFORM_THREADS_FOR_DEMO; i++) {
            final int threadId = i;
            // Create and start a new platform thread
            platformThreads[i] = Thread.ofPlatform().name("platform-thread-" + threadId).start(() -> {
                // System.out.println("Platform Thread: " + threadId); // Uncomment to see output, but it's very noisy
                latchPlatform.countDown();
            });
        }

        latchPlatform.await(); // Wait for all platform threads to complete
        long endTimePlatform = System.nanoTime();
        long durationPlatformMillis = TimeUnit.NANOSECONDS.toMillis(endTimePlatform - startTimePlatform);
        System.out.println("Finished launching and running " + NUM_PLATFORM_THREADS_FOR_DEMO + " platform threads in " + durationPlatformMillis + " ms");

        System.out.println("\n--- Performance Comparison ---");
        System.out.println("Virtual Threads (" + NUM_VIRTUAL_THREADS + " tasks): " + durationVirtualMillis + " ms");
        System.out.println("Platform Threads (" + NUM_PLATFORM_THREADS_FOR_DEMO + " tasks): " + durationPlatformMillis + " ms");

        if (NUM_VIRTUAL_THREADS > NUM_PLATFORM_THREADS_FOR_DEMO) {
            System.out.println("\nObservation: Notice how quickly " + NUM_VIRTUAL_THREADS + " virtual threads complete compared to a significantly smaller number of platform threads.");
            System.out.println("Virtual threads are designed for high concurrency with minimal resource overhead, making them ideal for I/O-bound tasks.");
        }
    }
}