package com.example.practice.spring.scope.management.mvc.handler.interceptor;

import com.example.practice.spring.scope.management.mvc.logger.RequestLogger;
import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestHandlerInterceptor implements HandlerInterceptor {
    private final RequestLogger requestLogger;

    private final RequestEvent requestEvent;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        requestLogger.info(
                "Request received"
        );

        requestEvent
                .getRequestEventProcess()
                .set((HandlerMethod) handler);

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex
    ) {
        requestLogger.info(
                "Request completed"
        );
    }
}
