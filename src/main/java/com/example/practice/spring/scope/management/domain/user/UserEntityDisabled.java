package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.domain.user.disabled.UserDisabledDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public final class UserEntityDisabled implements UserEntity {
    @Getter(onMethod_ = @Override)
    private final UserId userId;

    private final UserDisabledDateTime userDisabledDateTime;

    @Getter(onMethod_ = @Override)
    private final UserName userName;

    @Override
    public UserStatus getUserStatus() {
        return UserStatus.DISABLED;
    }
}
