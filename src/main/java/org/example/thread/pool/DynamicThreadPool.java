package org.example.thread.pool;

import org.example.thread.policy.WaitingThreadExceptionPolicy;

import java.util.concurrent.*;

public class DynamicThreadPool extends ThreadPoolExecutor {

    public static ThreadPoolExecutor newDynamicThreadPool(int corePoolSize, int maximumPoolSize) {
        return new DynamicThreadPool(corePoolSize, maximumPoolSize);
    }

    public static ThreadPoolExecutor newDynamicThreadPool(int corePoolSize, BlockingQueue<Runnable> queue) {
        return new DynamicThreadPool(corePoolSize, queue.size(), queue);
    }

    private DynamicThreadPool(int corePoolSize, int maximumPoolSize) {
        super(corePoolSize,
                maximumPoolSize,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(maximumPoolSize),
                new WaitingThreadExceptionPolicy());
    }

    private DynamicThreadPool(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> queue) {
        super(corePoolSize,
                maximumPoolSize,
                60L,
                TimeUnit.SECONDS,
                queue,
                new WaitingThreadExceptionPolicy());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        final WaitingThreadExceptionPolicy waitingHandler =
                (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();
        synchronized (this.getQueue()) {
            final Runnable poll = waitingHandler.poll();
            if (poll != null) {
                this.getQueue().offer(poll);
            }
        }
    }

    public Integer getWaitingQueueTaskCount() {
        final WaitingThreadExceptionPolicy waitingHandler =
                (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();

        return waitingHandler.getWaitingQueueCount();
    }
}
