package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.domain.user.active.UserActivatedDateTime;
import com.example.practice.spring.scope.management.domain.user.disabled.UserDisabledDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public final class UserEntityActive implements UserEntity {
    @Getter(onMethod_ = @Override)
    private final UserId userId;

    private final UserActivatedDateTime userActivatedDateTime;

    @Getter(onMethod_ = @Override)
    private final UserName userName;

    @Override
    public UserStatus getUserStatus() {
        return UserStatus.ACTIVE;
    }

    public UserEntityDisabled disable(UserDisabledDateTime userDisabledDateTime) {
        return new UserEntityDisabled(
                userId,
                userDisabledDateTime,
                userName
        );
    }
}
