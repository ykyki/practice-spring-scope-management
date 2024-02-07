package com.example.practice.spring.scope.management.domain.common.id;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IdPrefix {
    USER("USR");

    private final String value;

    public String formatWithEightDigits(Long number) {
        return value + String.format("%08d", number);
    }
}
