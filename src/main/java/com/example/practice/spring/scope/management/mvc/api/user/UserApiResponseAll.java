package com.example.practice.spring.scope.management.mvc.api.user;

import com.example.practice.spring.scope.management.domain.user.UserEntity;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public final class UserApiResponseAll {
    @JsonUnwrapped
    public final RequestResponseBase requestResponseBase;

    public final List<_User> userList;

    @AllArgsConstructor
    public static class _User {
        @JsonProperty
        public final String userId;

        @JsonProperty
        public final String userName;

        @JsonProperty
        public final String userStatus;
    }

    public static UserApiResponseAll build(
            RequestResponseBase requestResponseBase,
            io.vavr.collection.List<UserEntity> userEntityList
    ) {
        var userList = userEntityList
                .map(userEntity -> new _User(
                        userEntity.getUserId().getApiValue(),
                        userEntity.getUserName().getApiValue(),
                        userEntity.getUserStatus().getApiValue()
                ));

        return new UserApiResponseAll(
                requestResponseBase,
                userList.asJava()
        );
    }
}
