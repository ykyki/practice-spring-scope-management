package com.example.practice.spring.scope.management.mvc.api.util.template;

import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RequestResponseBaseFactory {
    private final RequestEvent requestEvent;

    public RequestResponseBase build() {
        return new RequestResponseBase(
                requestEvent.getRequestEventId().format(),
                requestEvent.getRequestEventDateTime().format_yyyyMMddHHmmssSSS()
        );
    }
}
