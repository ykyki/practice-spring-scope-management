package com.example.practice.spring.scope.management.mvc.logger;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEventDateTime;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiRequestLogger {
    private final RequestEventDateTime requestEventDateTime;

    private final static String LINE_FORMAT = "[Request:%s]%s";

    public void info(Class<?> clazz, String message) {
        _info(clazz.getName(), buildLogMessage(message));
    }

    private String buildLogMessage(String message) {
        return String.format(
                LINE_FORMAT,
                requestEventDateTime.format_yyyyMMddHHmmssSSS(),
                message
        );
    }

    private static void _info(String name, String message) {
        LoggerFactory.getLogger(name).info(message);
    }

    private static void _error(String name, String message) {
        LoggerFactory.getLogger(name).error(message);
    }
}
