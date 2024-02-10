package com.example.practice.spring.scope.management.domain.user.repository;

import com.example.practice.spring.scope.management.domain.common.RepositoryException;
import com.example.practice.spring.scope.management.domain.user.UserEntity;
import com.example.practice.spring.scope.management.domain.user.UserEntityActive;
import com.example.practice.spring.scope.management.domain.user.UserEntityDisabled;
import com.example.practice.spring.scope.management.domain.user.UserId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public interface UserRepository {
    Option<UserEntity> find(UserId userId);

    List<UserEntity> findAll();

    Validation<RepositoryException, UserEntityActive> activate(UserRepositoryNewActivationContainer container);

    Validation<RepositoryException, UserEntityDisabled> disable(UserRepositoryDisablingActiveUserContainer container);
}
