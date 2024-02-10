package com.example.practice.spring.scope.management.mvc.util.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RequestEventDateTime {
    private final LocalDateTime value;

    RequestEventDateTime() {
        // TODO
        value = LocalDateTime.now();
    }

    public LocalDateTime toLocalDateTime() {
        return value;
    }

    public String format_yyyyMMddHHmmss() {
        return toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public String format_yyyyMMddHHmmssSSS() {
        return toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }
}
