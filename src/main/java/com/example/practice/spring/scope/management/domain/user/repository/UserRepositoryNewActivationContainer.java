package com.example.practice.spring.scope.management.domain.user.repository;

import com.example.practice.spring.scope.management.domain.user.UserName;
import com.example.practice.spring.scope.management.domain.user.active.UserActivatedDateTime;

public record UserRepositoryNewActivationContainer(
        UserName userName,
        UserActivatedDateTime userActivatedDateTime
) {
}
