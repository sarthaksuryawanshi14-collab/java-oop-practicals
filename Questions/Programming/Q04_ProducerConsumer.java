/*
 * Q4. Implement the Producer-Consumer problem using wait() and notify()
 *     with a bounded buffer of size 3.
 */
class Buffer {
    private final int[] data = new int[3];
    private int count = 0, in = 0, out = 0;

    synchronized void put(int v) throws InterruptedException {
        while (count == data.length) wait();       // buffer full
        data[in] = v;
        in = (in + 1) % data.length;
        count++;
        System.out.println("Produced " + v);
        notifyAll();
    }

    synchronized int take() throws InterruptedException {
        while (count == 0) wait();                 // buffer empty
        int v = data[out];
        out = (out + 1) % data.length;
        count--;
        System.out.println("   Consumed " + v);
        notifyAll();
        return v;
    }
}

public class Q04_ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Buffer buf = new Buffer();
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) buf.put(i);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 8; i++) buf.take();
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Done");
    }
}
