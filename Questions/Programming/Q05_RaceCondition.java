/*
 * Q5. Demonstrate a race condition on a shared counter with 4 threads, then fix it
 *     using synchronized. Compare with an unsafe version.
 */
class UnsafeCounter {
    int count = 0;
    void increment() { count++; }                  // not atomic
}

class SafeCounter {
    private int count = 0;
    synchronized void increment() { count++; }
    synchronized int get() { return count; }
}

public class Q05_RaceCondition {
    static final int THREADS = 4, LOOPS = 100_000;

    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter unsafe = new UnsafeCounter();
        SafeCounter safe = new SafeCounter();

        Thread[] ts = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            ts[i] = new Thread(() -> {
                for (int j = 0; j < LOOPS; j++) {
                    unsafe.increment();
                    safe.increment();
                }
            });
            ts[i].start();
        }
        for (Thread t : ts) t.join();

        System.out.println("Expected      : " + THREADS * LOOPS);
        System.out.println("Unsafe counter: " + unsafe.count + " (may be less than expected: lost updates; run a few times)");
        System.out.println("Safe counter  : " + safe.get());
    }
}
