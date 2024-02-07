package com.example.practice.spring.scope.management.domain.user;

public sealed interface UserEntity permits UserEntityActive, UserEntityDisabled {
    UserId getUserId();

    UserStatus getUserStatus();

    UserName getUserName();
}
