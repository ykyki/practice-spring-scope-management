package com.example.practice.spring.scope.management.util.random;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomUtil {

    public boolean hit(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("percentage must be between 0 and 100");
        }

        return ThreadLocalRandom.current().nextInt(100) < percentage;
    }

    public int naturalNumber(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max must be non-negative");
        }

        return ThreadLocalRandom.current().nextInt(max + 1);
    }
}
