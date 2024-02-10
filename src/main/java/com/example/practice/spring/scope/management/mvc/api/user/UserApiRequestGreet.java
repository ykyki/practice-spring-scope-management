package com.example.practice.spring.scope.management.mvc.api.user;

import com.example.practice.spring.scope.management.mvc.api.form.UserIdForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public final class UserApiRequestGreet {
    @NotNull
    @Valid
    private UserIdForm userIdForm;
}
