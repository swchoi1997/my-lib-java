package org.example.thread.policy;

import java.util.concurrent.*;

public class WaitingThreadExceptionPolicy implements RejectedExecutionHandler {

    private final BlockingQueue<Runnable> blockingQueue;

    public WaitingThreadExceptionPolicy(){
        this(new LinkedBlockingQueue<>());
    }

    public WaitingThreadExceptionPolicy(BlockingQueue<Runnable> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        boolean added = this.blockingQueue.offer(r);
        if (!added) {
            throw new RejectedExecutionException();
        }
    }

    public BlockingQueue<Runnable> getBlockingQueue() {
        return blockingQueue;
    }

    public Runnable poll() {
        return this.blockingQueue.poll();
    }

    public Integer getWaitingQueueCount() {
        return this.blockingQueue.size();
    }
}
