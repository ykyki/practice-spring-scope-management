package com.example.practice.spring.scope.management.mvc.util.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@RequestScope
@Component
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestEvent {
    private final RequestEventId requestEventId;

    private final RequestEventDateTime requestEventDateTime;

    private final RequestEventProcess requestEventProcess;

    @Autowired
    public RequestEvent(RequestIdFactory requestIdFactory) {
        this(
                requestIdFactory.create(),
                new RequestEventDateTime(),
                new RequestEventProcess()
        );
    }
}
