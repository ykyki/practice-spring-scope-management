package com.example.practice.spring.scope.management.mvc.api.user.greet;

import com.example.practice.spring.scope.management.mvc.api.form.UserIdForm;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public final class UserApiGreetRequest {
    @NotNull
    @Valid
    private UserIdForm userIdForm;
}
