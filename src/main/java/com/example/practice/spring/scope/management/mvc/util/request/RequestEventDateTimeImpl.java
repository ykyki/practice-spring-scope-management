package com.example.practice.spring.scope.management.mvc.util.request;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@RequestScope
@Component
public class RequestEventDateTimeImpl implements RequestEventDateTime {
    private final LocalDateTime value;

    public RequestEventDateTimeImpl() {
        this.value = LocalDateTime.now();
    }

    @Override
    public LocalDateTime toLocalDateTime() {
        return value;
    }
}
