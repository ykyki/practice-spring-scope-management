package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.domain.common.id.IdPrefix;
import com.example.practice.spring.scope.management.domain.common.id.IsId;
import com.example.practice.spring.scope.management.mvc.api.util.mark.IsApiValue;

public record UserId(String value) implements IsId, IsApiValue {

    public UserId(long number) {
        this(IdPrefix.USER.formatWithEightDigits(number));
    }

    @Override
    public String getIdAsString() {
        return value;
    }

    @Override
    public String toString() {
        return getIdAsString();
    }

    @Override
    public String getApiValue() {
        return getIdAsString();
    }
}
