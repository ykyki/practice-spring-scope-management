package com.example.practice.spring.scope.management.integration.batch;

import org.springframework.core.annotation.AliasFor;
import org.springframework.integration.annotation.ServiceActivator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ServiceActivator
public @interface BatchMessageChannelServiceActivator {
    @AliasFor(annotation = ServiceActivator.class, attribute = "inputChannel")
    String inputChannel() default "";

    String[] adviceChain() default {BatchMessageChannelRequestAdvice.NAME};
}
