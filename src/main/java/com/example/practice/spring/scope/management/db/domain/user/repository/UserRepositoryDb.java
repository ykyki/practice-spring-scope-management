package com.example.practice.spring.scope.management.db.domain.user.repository;

import com.example.practice.spring.scope.management.domain.user.UserEntity;
import com.example.practice.spring.scope.management.domain.user.UserEntityActive;
import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.domain.user.UserName;
import com.example.practice.spring.scope.management.domain.user.active.UserActivatedDateTime;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepository;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepositoryDb implements UserRepository {
    @Override
    public Option<UserEntity> findById(UserId userId) {
        // TODO
        return Option.of(
                new UserEntityActive(
                        userId,
                        new UserActivatedDateTime(LocalDateTime.now()),
                        new UserName("Sample User Name")
                )
        );
    }
}
