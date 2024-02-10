package com.example.practice.spring.scope.management.domain.common.id;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IdPrefix {
    USER("USR");

    private final String value;

    public String formatWithEightDigits(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be non-negative");
        }

        return value + String.format("%08d", number);
    }
}
