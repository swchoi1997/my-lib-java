package org.example.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MultiThreadPool extends ThreadPool {

    public MultiThreadPool() {
        super(1, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(CAPACITY),
                new CallerRunsPolicy());
    }
    public MultiThreadPool(final int capacity){
        super(1, 1, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(capacity),
                new CallerRunsPolicy());
    }

    public void shutdownAndAwaitTermination() {

        super.shutdown();
        try {
            if (!super.awaitTermination(60, TimeUnit.SECONDS)) {
                this.shutdownNow();
                if (!super.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ex) {
            super.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
