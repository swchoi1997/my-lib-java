package org.example.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PausableSingleThreadPool extends ThreadPool {

    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    public PausableSingleThreadPool() {
        super(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(CAPACITY),
                new CallerRunsPolicy());
    }
    public PausableSingleThreadPool(final int capacity){
        super(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(capacity),
                new CallerRunsPolicy());
    }

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) unpaused.await();
        } catch (InterruptedException ie) {
            t.interrupt();
        } finally {
            pauseLock.unlock();
        }
    }
    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }
    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
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
