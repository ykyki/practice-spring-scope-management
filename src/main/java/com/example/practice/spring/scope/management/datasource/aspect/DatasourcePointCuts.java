package com.example.practice.spring.scope.management.datasource.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatasourcePointCuts {
    @Pointcut("execution(* com.example.practice.spring.scope.management.datasource.domain..*.*(..))")
    public void inDatasource() {
    }
}
