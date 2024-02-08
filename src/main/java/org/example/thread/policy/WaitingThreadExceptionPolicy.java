package org.example.thread.policy;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WaitingThreadExceptionPolicy implements RejectedExecutionHandler {

    private final BlockingQueue<Runnable> blockingQueue;
    private final AtomicBoolean mainQueueFullFlag;

    public WaitingThreadExceptionPolicy(final AtomicBoolean mainQueueFullFlag){
        this(new LinkedBlockingQueue<>(), mainQueueFullFlag);
    }

    public WaitingThreadExceptionPolicy(BlockingQueue<Runnable> blockingQueue, final AtomicBoolean mainQueueFullFlag) {
        this.blockingQueue = blockingQueue;
        this.mainQueueFullFlag = mainQueueFullFlag;

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("test123123123");
        if (!this.mainQueueFullFlag.get()) {
            this.mainQueueFullFlag.set(true);
        }

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

    public AtomicBoolean getMainQueueFullFlag() {
        return mainQueueFullFlag;
    }
}
