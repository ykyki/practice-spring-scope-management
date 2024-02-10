package com.example.practice.spring.scope.management.mvc.api.form;

import com.example.practice.spring.scope.management.domain.user.UserName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public final class UserNameForm implements IsForm<UserName> {
    @NotBlank
    private final String value;

    @Override
    public UserName getFormValue() {
        return new UserName(value);
    }
}
