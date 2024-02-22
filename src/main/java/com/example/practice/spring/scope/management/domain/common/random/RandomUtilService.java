package com.example.practice.spring.scope.management.domain.common.random;

public interface RandomUtilService {
    boolean hit(int percentage);

    int naturalNumber(int max);
}
