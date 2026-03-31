import java.util.*;
import java.util.concurrent.*;

// ─────────────────────────────────────────────
// Q1: Find Prime Numbers using Multithreading
//     (Thread, Runnable, Executor Framework)
// ─────────────────────────────────────────────

// ── Helper ──────────────────────────────────
class PrimeUtils {
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }
}

// ── Approach 1: extends Thread ───────────────
class PrimeThread extends Thread {
    private final int start, end;

    PrimeThread(int start, int end) {
        this.start = start;
        this.end   = end;
    }

    @Override
    public void run() {
        System.out.print("[Thread] Primes in [" + start + "–" + end + "]: ");
        for (int i = start; i <= end; i++)
            if (PrimeUtils.isPrime(i)) System.out.print(i + " ");
        System.out.println();
    }
}

// ── Approach 2: implements Runnable ──────────
class PrimeRunnable implements Runnable {
    private final int start, end;

    PrimeRunnable(int start, int end) {
        this.start = start;
        this.end   = end;
    }

    @Override
    public void run() {
        System.out.print("[Runnable] Primes in [" + start + "–" + end + "]: ");
        for (int i = start; i <= end; i++)
            if (PrimeUtils.isPrime(i)) System.out.print(i + " ");
        System.out.println();
    }
}

// ── Approach 3: Executor Framework ───────────
class PrimeTask implements Runnable {
    private final int start, end;

    PrimeTask(int start, int end) {
        this.start = start;
        this.end   = end;
    }

    @Override
    public void run() {
        System.out.print("[Executor:" + Thread.currentThread().getName() + "] "
                + "Primes in [" + start + "–" + end + "]: ");
        for (int i = start; i <= end; i++)
            if (PrimeUtils.isPrime(i)) System.out.print(i + " ");
        System.out.println();
    }
}

public class Q1_PrimeMultithreading {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Approach 1: extends Thread ===");
        PrimeThread t1 = new PrimeThread(1, 50);
        PrimeThread t2 = new PrimeThread(51, 100);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("\n=== Approach 2: implements Runnable ===");
        Thread r1 = new Thread(new PrimeRunnable(1, 50));
        Thread r2 = new Thread(new PrimeRunnable(51, 100));
        r1.start(); r2.start();
        r1.join();  r2.join();

        System.out.println("\n=== Approach 3: Executor Framework ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new PrimeTask(1,   33));
        executor.submit(new PrimeTask(34,  66));
        executor.submit(new PrimeTask(67, 100));
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
