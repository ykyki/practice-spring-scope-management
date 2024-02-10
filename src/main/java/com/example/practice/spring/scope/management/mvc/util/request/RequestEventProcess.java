package com.example.practice.spring.scope.management.mvc.util.request;

import io.vavr.control.Option;

public final class RequestEventProcess {
    private Option<String> nameOption;

    public RequestEventProcess() {
        this.nameOption = Option.none();
    }

    public void initialize(String name) {
        // if (this.nameOption.isDefined()) {
        //     throw new IllegalStateException("RequestEventProcess is already initialized as " + this.nameOption.get());
        // }

        this.nameOption = Option.of(name);
    }

    public String getName() {
        return nameOption.getOrElse(() -> "<UninitializedProcessName>");
    }
}
