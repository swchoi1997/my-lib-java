//package org.example.delegate;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ActionTest {
//
//    @Test
//    public void threadInterruptTest() throws InterruptedException {
//
//        Thread myThread = new MyThread(() -> {
//            for (int j = 0; j <= 2100000000; ++j) {
//                int cnt = 0;
//                while (true) {
//                    cnt++;
//                    if (cnt % 100000000 == 0) {
//                        System.out.println(cnt);
//                    }
//
//                    if (cnt >= 2100000000) {
//
//                        break;
//                    }
//                }
//            }
//        });
//        myThread.start();
//
//        LocalDateTime now = LocalDateTime.now();
//
//        Thread.sleep(3000);
//        System.out.println(Duration.between(now, LocalDateTime.now()).toMillis());
////        myThread.interrupt();
//
//
//
//        System.out.println("aaaaaaaaa");
//        myThread.join();
//
//
//    }
//
//}
//
//class MyThread extends Thread {
//    private final Action action;
//
//    public MyThread(final Action action) {
//        this.action = action;
//    }
//
//    @Override
//    public void run() {
//        try {
//            this.action.invoke();
//        } catch (InterruptedException e) {
//            this.interrupt();
//            throw new RuntimeException(e);
//        }
//    }
//}