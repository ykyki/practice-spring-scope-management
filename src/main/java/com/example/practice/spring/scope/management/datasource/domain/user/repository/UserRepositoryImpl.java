package com.example.practice.spring.scope.management.datasource.domain.user.repository;

import com.example.practice.spring.scope.management.domain.common.RepositoryException;
import com.example.practice.spring.scope.management.domain.user.UserEntity;
import com.example.practice.spring.scope.management.domain.user.UserEntityActive;
import com.example.practice.spring.scope.management.domain.user.UserEntityDisabled;
import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepository;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepositoryDisablingActiveUserContainer;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepositoryNewActivationContainer;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final AtomicLong atomicLong;

    private final ConcurrentHashMap<UserId, UserEntity> userEntityMap;

    public UserRepositoryImpl() {
        this.atomicLong = new AtomicLong(0);
        this.userEntityMap = new ConcurrentHashMap<>();
    }

    @Override
    public Option<UserEntity> find(UserId userId) {
        return Option.of(userEntityMap.get(userId));
    }

    @Override
    public List<UserEntity> findAll() {
        return List.ofAll(userEntityMap.values());
    }

    @Override
    public Validation<RepositoryException, UserEntityActive> activate(UserRepositoryNewActivationContainer container) {
        var userId = createUserId();
        var userEntityActive = new UserEntityActive(
                userId,
                container.userActivatedDateTime(),
                container.userName()
        );

        userEntityMap.put(userEntityActive.getUserId(), userEntityActive);

        return Validation.valid(userEntityActive);
    }

    @Override
    public Validation<RepositoryException, UserEntityDisabled> disable(UserRepositoryDisablingActiveUserContainer container) {
        var userEntityOption = find(container.userId());

        if (userEntityOption.isEmpty()) {
            return Validation.invalid(new RepositoryException("User not found"));
        }
        var userEntity = userEntityOption.get();

        if (!userEntity.getUserStatus().isActive()) {
            return Validation.invalid(new RepositoryException("User is not active"));
        }
        var userEntityActive = (UserEntityActive) userEntity;

        var userEntityDisabled = userEntityActive.disable(
                container.userDisabledDateTime()
        );

        userEntityMap.put(userEntityDisabled.getUserId(), userEntityDisabled);

        return Validation.valid(userEntityDisabled);
    }

    private UserId createUserId() {
        return new UserId(atomicLong.incrementAndGet());
    }

}
