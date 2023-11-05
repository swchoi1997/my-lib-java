package org.example.thread.pool;

import java.util.concurrent.*;

public class SingleThreadPool extends ThreadPool {

    public SingleThreadPool() {
        super(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    public SingleThreadPool(final int capacity){
        super(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(capacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
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
