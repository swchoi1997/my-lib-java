package org.example.random;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class RandomPickerTest {

    @Test
    public void pickTest() throws Exception{
        List<Integer> targets = List.of(1, 2, 3);
        List<Integer> weight = List.of(1, 2, 3);

        RandomPicker randomPicker = new RandomPicker(targets, weight);

        ConcurrentHashMap<Integer, Integer> result = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        int pickCnt = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(pickCnt);

        for (int i = 0; i < pickCnt; i++) {
            executorService.submit(() -> {
                synchronized (randomPicker) {
                    try {
                        int pick = (int) randomPicker.pick();
                        result.compute(pick, (key, val) -> (val == null) ? 1 : val + 1);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        int all = result.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        List<Double> percentages = result.values()
                .stream()
                .mapToInt(Integer::intValue)
                .mapToObj(v -> (double) v / all * 100.0)
                .toList();

        System.out.println(all);
        System.out.println(result);
        percentages.forEach(p -> System.out.println(p + "%"));
    }

}