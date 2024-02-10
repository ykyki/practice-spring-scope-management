package com.example.practice.spring.scope.management.mvc.api.form;

import com.example.practice.spring.scope.management.domain.user.UserId;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
public final class UserIdForm implements IsForm<UserId> {
    @Pattern(regexp = "^USR[0-9]{8}$")
    private final String value;

    @Override
    public UserId getFormValue() {
        return new UserId(value);
    }
}
