package com.example.practice.spring.scope.management.mvc.util.request;

public record RequestEventId(long value) {
    public RequestEventId {
        if (value < 0) {
            throw new IllegalArgumentException("RequestID must be non-negative");
        }
    }

    public String format() {
        return String.format("%010d", value);
    }
}
