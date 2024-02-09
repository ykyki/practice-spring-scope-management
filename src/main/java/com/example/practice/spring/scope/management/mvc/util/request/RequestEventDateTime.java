package com.example.practice.spring.scope.management.mvc.util.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface RequestEventDateTime {
    LocalDateTime toLocalDateTime();

    default String format_yyyyMMddHHmmss() {
        return toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    default String format_yyyyMMddHHmmssSSS() {
        return toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }
}
