package com.example.practice.spring.scope.management.api.user;

import com.example.practice.spring.scope.management.api.util.template.RequestResponseMapBuilder;
import com.example.practice.spring.scope.management.domain.user.UserId;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
final class UserApi {
    private final UserRepository userRepository;

    private final RequestResponseMapBuilder requestResponseMapBuilder;


    @GetMapping("/greet")
    public RequestResponseMapBuilder.Response greet() {
        var userEntityOption = userRepository.findById(new UserId(1234L));

        if (userEntityOption.isEmpty()) {
            return requestResponseMapBuilder.build(
                    Map.of("message", "User not found.")
            );
        }

        var userEntity = userEntityOption.get();

        return requestResponseMapBuilder.build(
                Map.of(
                        "message", "Hello " + userEntity.getUserName().getApiValue() + "!",
                        "userId", userEntity.getUserId().getApiValue(),
                        "userStatus", userEntity.getUserStatus().getApiValue()
                )
        );
    }
}
