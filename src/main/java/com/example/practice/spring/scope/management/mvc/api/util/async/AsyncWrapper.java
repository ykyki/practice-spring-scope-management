package com.example.practice.spring.scope.management.mvc.api.util.async;

import com.example.practice.spring.scope.management.mvc.logger.RequestLogger;
import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AsyncWrapper {
    private final RequestLogger requestLogger;

    private final RequestEvent requestEvent;

    @Async
    public <T> CompletableFuture<T> call(Callable<T> callable) {
        requestLogger.info(
                "AsyncWrapperCall(time:%s, thread:%s)",
                requestEvent.getRequestEventDateTime().format_yyyyMMddHHmmssSSS(),
                Thread.currentThread().threadId()
        );

        T result;
        try {
            result = callable.call();
        } catch (Exception e) {
            requestLogger.error("Thread interrupted: ", e.getMessage());
            throw new RuntimeException(e);
        }

        return CompletableFuture.completedFuture(result);
    }
}
