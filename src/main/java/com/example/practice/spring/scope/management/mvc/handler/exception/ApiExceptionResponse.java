package com.example.practice.spring.scope.management.mvc.handler.exception;

import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
final class ApiExceptionResponse implements Serializable {
    @JsonUnwrapped
    public final RequestResponseBase requestResponseBase;

    public final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public final List<_Detail> details;

    @AllArgsConstructor
    public static class _Detail implements Serializable {
        public final String target;
        public final String message;
    }
}
