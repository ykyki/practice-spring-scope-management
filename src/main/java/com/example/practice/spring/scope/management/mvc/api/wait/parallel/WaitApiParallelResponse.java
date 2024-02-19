package com.example.practice.spring.scope.management.mvc.api.wait.parallel;

import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class WaitApiParallelResponse {
    @JsonUnwrapped
    public final RequestResponseBase requestResponseBase;

    public final List<String> results;

    public static WaitApiParallelResponse build(
            RequestResponseBase requestResponseBase,
            io.vavr.collection.List<String> results
    ) {
        return new WaitApiParallelResponse(
                requestResponseBase,
                results.asJava()
        );
    }
}
