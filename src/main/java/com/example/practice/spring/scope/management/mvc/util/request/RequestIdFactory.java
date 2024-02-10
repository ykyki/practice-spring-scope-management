package com.example.practice.spring.scope.management.mvc.util.request;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestIdFactory {
    private final AtomicLong atomicLong;

    RequestIdFactory() {
        this.atomicLong = new AtomicLong(0);
    }

    public RequestEventId create() {
        return new RequestEventId(atomicLong.incrementAndGet());
    }
}
