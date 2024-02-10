package com.example.practice.spring.scope.management.domain.user.repository;

import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.domain.user.disabled.UserDisabledDateTime;

public record UserRepositoryDisablingActiveUserContainer(
        UserId userId,
        UserDisabledDateTime userDisabledDateTime
) {
}
