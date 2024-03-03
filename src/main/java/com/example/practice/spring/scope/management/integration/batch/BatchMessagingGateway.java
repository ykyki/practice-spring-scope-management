package com.example.practice.spring.scope.management.integration.batch;


import org.springframework.integration.annotation.MessagingGateway;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MessagingGateway(defaultRequestTimeout = "1000")
public @interface BatchMessagingGateway {
    String channelName();
}
