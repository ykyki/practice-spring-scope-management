package com.example.practice.spring.scope.management.util.sleep;

import org.springframework.stereotype.Service;

@Service
public class SleepUtil {

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
