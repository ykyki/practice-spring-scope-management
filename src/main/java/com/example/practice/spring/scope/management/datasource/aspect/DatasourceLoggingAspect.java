package com.example.practice.spring.scope.management.datasource.aspect;

import com.example.practice.spring.scope.management.mvc.logger.RequestLogger;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DatasourceLoggingAspect {
    private final RequestLogger requestLogger;

    @Before(value = "com.example.practice.spring.scope.management.datasource.aspect.DatasourcePointCuts.inDatasource()")
    public void logDatasourceCallBefore(JoinPoint joinPoint) {
        requestLogger.info(String.format(
                "Datasource call before: %s, args:(%s)",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs())
        ));
    }

    @AfterReturning(value = "com.example.practice.spring.scope.management.datasource.aspect.DatasourcePointCuts.inDatasource()", returning = "returning")
    public void logDatasourceCallAfter(JoinPoint joinPoint, Object returning) {
        requestLogger.info(String.format(
                "Datasource call after: %s, returning:(%s)",
                joinPoint.getSignature().toShortString(),
                returning
        ));
    }

    @AfterThrowing(value = "com.example.practice.spring.scope.management.datasource.aspect.DatasourcePointCuts.inDatasource()", throwing = "exception")
    public void logDatasourceCallAfterThrowing(JoinPoint joinPoint, Exception exception) {
        requestLogger.error(String.format(
                "Datasource call after throwing: %s, exception:(%s)",
                joinPoint.getSignature().toShortString(),
                exception
        ));
    }
}
