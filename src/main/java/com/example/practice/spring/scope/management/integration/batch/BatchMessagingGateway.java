package com.example.practice.spring.scope.management.integration.batch;


import org.springframework.core.annotation.AliasFor;
import org.springframework.integration.annotation.MessagingGateway;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MessagingGateway(defaultRequestTimeout = BatchMessagingGateway.DEFAULT_REQUEST_TIMEOUT_STR)
public @interface BatchMessagingGateway {
    String DEFAULT_REQUEST_TIMEOUT_STR = "1000";

    @AliasFor(annotation = MessagingGateway.class, attribute = "defaultRequestChannel")
    String defaultRequestChannel() default "";
}
