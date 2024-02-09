package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.mvc.api.util.mark.IsApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserStatus implements IsApiValue {
    ACTIVE("有効", "active"),
    DISABLED("無効化済み", "disabled");

    private final String name;

    @Getter(onMethod_ = @Override)
    private final String apiValue;
}
