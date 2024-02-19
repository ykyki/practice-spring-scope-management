package com.example.practice.spring.scope.management.mvc.util.request.mutable;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import com.example.practice.spring.scope.management.mvc.util.request.RequestEventDateTime;
import com.example.practice.spring.scope.management.mvc.util.request.RequestIdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Primary
@Component
public class RequestEventMutable extends RequestEvent {
    private final RequestEventDateTime originalRequestEventDateTime;

    private final RequestEventMutableEventDateTimeSetter requestEventMutableEventDateTimeSetter;

    @Autowired
    public RequestEventMutable(
            RequestIdFactory requestIdFactory,
            RequestEventMutableEventDateTimeSetter requestEventMutableEventDateTimeSetter
    ) {
        super(requestIdFactory);
        this.requestEventMutableEventDateTimeSetter = requestEventMutableEventDateTimeSetter;
        this.originalRequestEventDateTime = super.getRequestEventDateTime();
    }

    @Override
    public RequestEventDateTime getRequestEventDateTime() {
        return requestEventMutableEventDateTimeSetter
                .getRequestEventDateTimeOption()
                .getOrElse(originalRequestEventDateTime);
    }
}
