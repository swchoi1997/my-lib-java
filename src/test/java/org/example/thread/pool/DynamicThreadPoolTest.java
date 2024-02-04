package org.example.thread.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

class DynamicThreadPoolTest {

    @Test
    void poolTest() {
        ThreadPoolExecutor executor =
                DynamicThreadPool.newDynamicThreadPool(2, 4);

        for (int i = 0; i < 10; i++) {
            final int jobCnt = i;
            executor.submit(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(Thread.currentThread().getName() + " ->>>>>> Running" + jobCnt);
            });
        }

        DynamicThreadPool dynamicThreadPool = (DynamicThreadPool) executor;


        int cnt = 0;
        while (cnt <= 15) {
            System.out.println("Queue Size : " + executor.getQueue().size());
            System.out.println("WaitingQueue Size " + dynamicThreadPool.getWaitingQueueTaskCount());
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            cnt += 1;
        }


        executor.shutdown();


    }

}