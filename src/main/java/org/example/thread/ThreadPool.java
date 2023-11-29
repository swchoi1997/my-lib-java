package org.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool{

    private final ExecutorService executor;

    private final List<Future<?>> futureList;

    private final ThreadExceptionPolicy policy;

    private final Object lock = new Object();



    public ThreadPool() {
        this(ThreadExceptionPolicy.CONTINUE);
    }

    public ThreadPool(final ThreadExceptionPolicy policy) {
        this(Executors.newCachedThreadPool(), new ArrayList<>(), policy);
    }

    public ThreadPool(final int poolSize) {
        this(poolSize, ThreadExceptionPolicy.CONTINUE);
    }

    public ThreadPool(final int poolSize, final ThreadExceptionPolicy policy) {
        this(Executors.newFixedThreadPool(poolSize), new ArrayList<>(), policy);
    }

    private ThreadPool(final ExecutorService executor,
                       final List<Future<?>> futureList,
                       final ThreadExceptionPolicy policy) {
        this.executor = executor;
        this.futureList = futureList;
        this.policy = policy;

        this.monitorFutures();
    }

    public void submit(final Runnable runnable) {
        Future<?> submit = this.executor.submit(runnable);
        synchronized (lock) {
            this.futureList.add(submit);
        }
    }

    public <T> void submit(final Callable<T> callable) {
        Future<T> submit = this.executor.submit(callable);
        synchronized (lock) {
            this.futureList.add(submit);
        }
    }

    public void submitBulkRunnable(final List<Runnable> runnables) {
        runnables.forEach(this::submit);
    }

    public <T> void submitBulkCallable(final List<Callable<T>> callables) {
        callables.forEach(this::submit);
    }

    private void monitorFutures() {
        new Thread(() -> {
            while (!executor.isShutdown()) {
                for (Future<?> future : futureList) {
                    handleCompletedFuture(future);
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private void handleCompletedFuture(final Future<?> future) {
        synchronized (lock) {
            if (future.isDone() || future.isCancelled()) {
                try {
                    future.get();
                } catch (Exception e) {
                    if (this.policy == ThreadExceptionPolicy.CONTINUE){
                        return;
                    }
                    this.shutdown();
                }
            }
        }
    }

    public void shutdown() {
        for (Future<?> future : this.futureList) {
            future.cancel(true);
        }

        this.executor.shutdown();
        try {
            if (!this.executor.awaitTermination(10, TimeUnit.SECONDS)) {
                this.executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            this.executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


}
