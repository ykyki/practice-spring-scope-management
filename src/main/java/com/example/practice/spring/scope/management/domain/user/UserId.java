package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.domain.common.id.IdPrefix;
import com.example.practice.spring.scope.management.domain.common.id.IsId;
import com.example.practice.spring.scope.management.mvc.api.util.mark.IsApiValue;

public record UserId(long number) implements IsId, IsApiValue {
    @Override
    public String getIdAsString() {
        return IdPrefix.USER.formatWithEightDigits(number);
    }

    @Override
    public String getApiValue() {
        return getIdAsString();
    }
}
