import java.util.LinkedList;
import java.util.Queue;

// ──────────────────────────────────────────────────────────────
// Q2: Producer–Consumer using Synchronization & Inter-thread
//     Communication (wait / notify)
// ──────────────────────────────────────────────────────────────

class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    // Producer calls this
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            System.out.println("[Producer] Buffer full. Waiting…");
            wait();                          // release lock, wait for space
        }
        queue.add(item);
        System.out.println("[Producer] Produced: " + item
                + "  | Buffer size: " + queue.size());
        notifyAll();                         // wake up consumer(s)
    }

    // Consumer calls this
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("[Consumer] Buffer empty. Waiting…");
            wait();                          // release lock, wait for item
        }
        int item = queue.poll();
        System.out.println("[Consumer] Consumed: " + item
                + "  | Buffer size: " + queue.size());
        notifyAll();                         // wake up producer(s)
        return item;
    }
}

class Producer implements Runnable {
    private final SharedBuffer buffer;

    Producer(SharedBuffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(300);           // simulate production delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final SharedBuffer buffer;

    Consumer(SharedBuffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.consume();
                Thread.sleep(500);           // consume slower than produce
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Q2_ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("\nAll items produced and consumed successfully.");
    }
}
