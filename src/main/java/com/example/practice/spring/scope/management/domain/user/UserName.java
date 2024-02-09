package com.example.practice.spring.scope.management.domain.user;

import com.example.practice.spring.scope.management.mvc.api.util.mark.IsApiValue;

public record UserName(String name) implements IsApiValue {

    @Override
    public String getApiValue() {
        return name;
    }
}
