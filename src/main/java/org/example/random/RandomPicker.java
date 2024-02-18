package org.example.random;

import java.util.List;
import java.util.Random;

public class RandomPicker {

    private final List<?> targets;

    private final List<Integer> weights;

    private final Integer totalWeight;

    private Random random;

    public RandomPicker(final List<?> targets,
                        final List<Integer> weights) {
        this.targets = targets;
        this.weights = weights;
        this.totalWeight = this.calculateTotalWeight(weights);
        this.random = new Random();
    }

    private Integer calculateTotalWeight(final List<Integer> weights) {
        return weights.stream().mapToInt(Integer::intValue).sum();
    }

    public Object pick() {
        int randomNumber = random.nextInt(totalWeight) + 1;

        int accumulatedWeight = 0;

        for (int i = 0; i < targets.size(); i++) {
            accumulatedWeight += weights.get(i);
            if(randomNumber <= accumulatedWeight) {
                return targets.get(i);
            }
        }

        /* Never Reached */
        throw new IllegalStateException();
    }
}
