package com.example.practice.spring.scope.management.mvc.api.util.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public final class RequestResponseBase implements Serializable {
    @JsonProperty
    public final String requestEventId;
}
