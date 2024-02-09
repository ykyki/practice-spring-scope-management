package com.example.practice.spring.scope.management.mvc.api.util.template;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEventDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;

@RequestScope
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestResponseMapBuilder {
    private final RequestEventDateTime requestEventDateTime;

    public RequestResponseMapBuilder.Response build(Map<String, Object> responceMap) {
        return new RequestResponseMapBuilder.Response(
                requestEventDateTime.format_yyyyMMddHHmmss(),
                responceMap
        );
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        @JsonProperty
        public final String requestEventDateTime;

        @JsonUnwrapped
        public final Map<String, Object> responceMap;
    }
}
