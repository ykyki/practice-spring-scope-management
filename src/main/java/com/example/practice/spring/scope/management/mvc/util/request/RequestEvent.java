package com.example.practice.spring.scope.management.mvc.util.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestEvent {
    @Getter
    private final RequestEventId requestEventId;

    @Getter
    private final RequestEventDateTime requestEventDateTime;

    @Autowired
    public RequestEvent(RequestIdFactory requestIdFactory) {
        this(
                requestIdFactory.create(),
                new RequestEventDateTime()
        );
    }
}
