package org.example.thread.pool;

import org.example.thread.policy.WaitingThreadExceptionPolicy;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public final class DynamicThreadPool extends ThreadPoolExecutor {

    private final AtomicBoolean mainQueueFullFlag;

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
                new WaitingThreadExceptionPolicy(new AtomicBoolean(false)));

        final WaitingThreadExceptionPolicy handler = (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();
        this.mainQueueFullFlag = handler.getMainQueueFullFlag();
    }

    private DynamicThreadPool(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> queue) {
        super(corePoolSize,
                maximumPoolSize,
                60L,
                TimeUnit.SECONDS,
                queue,
                new WaitingThreadExceptionPolicy(new AtomicBoolean(false)));

        final WaitingThreadExceptionPolicy handler = (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();
        this.mainQueueFullFlag = handler.getMainQueueFullFlag();
    }

    @Override
    public void execute(Runnable command) {
        if (this.mainQueueFullFlag.get()) {
            final WaitingThreadExceptionPolicy handler =
                    (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();

            handler.getBlockingQueue().offer(command);
            return;
        }

        super.execute(command);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        final WaitingThreadExceptionPolicy handler =
                (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();

        synchronized (this.getQueue()) {
            final Runnable poll = handler.poll();
            if (poll != null) {
                this.getQueue().offer(poll);
            }

            if (this.getQueue().size() < this.getMaximumPoolSize() && handler.getBlockingQueue().isEmpty()) {
                this.mainQueueFullFlag.compareAndSet(true, false);
            }
        }
        super.beforeExecute(t, r);
    }

    public Integer getWaitingQueueTaskCount() {
        final WaitingThreadExceptionPolicy waitingHandler =
                (WaitingThreadExceptionPolicy) this.getRejectedExecutionHandler();

        return waitingHandler.getWaitingQueueCount();
    }


    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                DynamicThreadPool.newDynamicThreadPool(2, 10);

        for (int i = 0; i < 100; i++) {
            final int jobCnt = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " ->>>>>> Running" + jobCnt);
            });
        }
        int cnt = 0;
        while (true) {
            WaitingThreadExceptionPolicy rejectedExecutionHandler = (WaitingThreadExceptionPolicy) executor.getRejectedExecutionHandler();
            System.out.println(rejectedExecutionHandler.getBlockingQueue().size());
            try {
                Thread.sleep(200);
                cnt += 1;
                if (cnt % 10 == 0){
                    for (int i = 0; i < 20; i++) {
                        final int jobCnt = i;
                        executor.submit(() -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(Thread.currentThread().getName() + " ->>>>>> Running" + jobCnt);
                        });
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
