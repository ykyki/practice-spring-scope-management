package com.example.practice.spring.scope.management.mvc.logger;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestLogger {
    private final RequestEvent requestEvent;

    private final static String LINE_FORMAT = "[Request:%s]%s";

    public void info(String format, Object... args) {
        _info(
                requestEvent.getRequestEventProcess().getName(),
                buildLogMessage(String.format(format, args))
        );
    }

    public void error(String format, Object... args) {
        _error(
                requestEvent.getRequestEventProcess().getName(),
                buildLogMessage(String.format(format, args))
        );
    }

    private String buildLogMessage(String message) {
        return String.format(
                LINE_FORMAT,
                requestEvent.getRequestEventId().format(),
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
