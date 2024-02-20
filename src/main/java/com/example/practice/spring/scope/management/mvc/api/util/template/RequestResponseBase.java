package com.example.practice.spring.scope.management.mvc.api.util.template;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public final class RequestResponseBase implements Serializable {
    public final String requestEventId;
    public final String requestEventDateTime;
}
