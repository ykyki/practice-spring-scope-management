package com.example.practice.spring.scope.management.domain.user.repository;

import com.example.practice.spring.scope.management.domain.user.UserEntity;
import com.example.practice.spring.scope.management.domain.user.UserId;
import io.vavr.control.Option;

public interface UserRepository {
    Option<UserEntity> findById(UserId userId);
}
