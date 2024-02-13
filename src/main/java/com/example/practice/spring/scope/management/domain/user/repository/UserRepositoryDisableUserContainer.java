package com.example.practice.spring.scope.management.domain.user.repository;

import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.domain.user.disabled.UserDisabledDateTime;

public record UserRepositoryDisableUserContainer(
        UserId userId,
        UserDisabledDateTime userDisabledDateTime
) {
}
