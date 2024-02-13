package com.example.practice.spring.scope.management.mvc.api.user.newuser;

import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class UserApiNewUserResponse {
    @JsonUnwrapped
    public final RequestResponseBase requestResponseBase;

    public final String userId;

    public static UserApiNewUserResponse build(
            RequestResponseBase requestResponseBase,
            UserId userId
    ) {
        return new UserApiNewUserResponse(
                requestResponseBase,
                userId.getApiValue()
        );
    }
}
