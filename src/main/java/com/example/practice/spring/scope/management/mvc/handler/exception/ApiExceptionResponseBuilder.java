package com.example.practice.spring.scope.management.mvc.handler.exception;

import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
final class ApiExceptionResponseBuilder {
    private final RequestResponseBase requestResponseBase;

    private String message;

    public ApiExceptionResponseBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    private List<ApiExceptionResponse._Detail> details = new ArrayList<>();

    public ApiExceptionResponseBuilder setDetails(List<ApiExceptionResponse._Detail> details) {
        this.details = details;
        return this;
    }

    public ApiExceptionResponseBuilder addDetail(String target, String message) {
        details.add(new ApiExceptionResponse._Detail(target, message));
        return this;
    }

    public ApiExceptionResponse build() {
        return new ApiExceptionResponse(
                requestResponseBase,
                message,
                Collections.unmodifiableList(details)
        );
    }
}
