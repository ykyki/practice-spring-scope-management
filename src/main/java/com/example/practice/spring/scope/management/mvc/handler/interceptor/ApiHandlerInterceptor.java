package com.example.practice.spring.scope.management.mvc.handler.interceptor;

import com.example.practice.spring.scope.management.mvc.logger.ApiRequestLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiHandlerInterceptor implements HandlerInterceptor {
    private final ApiRequestLogger apiRequestLogger;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        apiRequestLogger.info(
                ApiHandlerInterceptor.class,
                "Request received"
        );

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex
    ) {
        apiRequestLogger.info(
                ApiHandlerInterceptor.class,
                "Request completed"
        );
    }
}