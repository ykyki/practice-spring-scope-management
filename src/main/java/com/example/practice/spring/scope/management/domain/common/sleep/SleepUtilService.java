package com.example.practice.spring.scope.management.domain.common.sleep;

import org.springframework.stereotype.Service;

@Service
public class SleepUtilService {

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
