package com.example.practice.spring.scope.management.mvc.util.request;

import io.vavr.control.Option;
import org.springframework.web.method.HandlerMethod;

public final class RequestEventProcess {
    private Option<String> nameOption;

    public RequestEventProcess() {
        this.nameOption = Option.none();
    }

    public void set(HandlerMethod handlerMethod) {
        var name = String.format(
                "%s.%s",
                handlerMethod.getBeanType().getSimpleName(),
                handlerMethod.getMethod().getName()
        );

        this.nameOption = Option.of(name);
    }

    public String getName() {
        return nameOption.getOrElse(() -> "<UninitializedProcessName>");
    }
}
