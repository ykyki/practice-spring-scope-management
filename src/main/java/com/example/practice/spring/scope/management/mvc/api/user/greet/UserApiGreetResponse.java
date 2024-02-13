package com.example.practice.spring.scope.management.mvc.api.user.greet;

import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserApiGreetResponse {
    @JsonUnwrapped
    public final RequestResponseBase requestResponseBase;

    public final String message;

    public final String userId;

    public final String userStatus;

    public static UserApiGreetResponse build(
            RequestResponseBase requestResponseBase,
            String message,
            String userId,
            String userStatus
    ) {
        return new UserApiGreetResponse(
                requestResponseBase,
                message,
                userId,
                userStatus
        );
    }
}
