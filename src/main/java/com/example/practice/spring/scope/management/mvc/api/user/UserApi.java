package com.example.practice.spring.scope.management.mvc.api.user;

import com.example.practice.spring.scope.management.domain.user.active.UserActivatedDateTime;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepository;
import com.example.practice.spring.scope.management.domain.user.repository.UserRepositoryActivateNewUserContainer;
import com.example.practice.spring.scope.management.mvc.api.user.all.UserApiAllResponse;
import com.example.practice.spring.scope.management.mvc.api.user.greet.UserApiGreetRequest;
import com.example.practice.spring.scope.management.mvc.api.user.greet.UserApiGreetResponse;
import com.example.practice.spring.scope.management.mvc.api.user.newuser.UserApiNewUserRequest;
import com.example.practice.spring.scope.management.mvc.api.user.newuser.UserApiNewUserResponse;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBaseFactory;
import com.example.practice.spring.scope.management.mvc.logger.ApiRequestLogger;
import com.example.practice.spring.scope.management.mvc.util.request.RequestEvent;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
final class UserApi {
    private final UserRepository userRepository;

    private final RequestResponseBaseFactory requestResponseBaseFactory;

    private final ApiRequestLogger apiRequestLogger;

    private final RequestEvent requestEvent;

    private final static String PATH = "/api/user";


    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public ResponseEntity<UserApiAllResponse> invoke() {
        var userEntityList = userRepository.findAll();

        return ResponseEntity
                .ok()
                .body(UserApiAllResponse.build(
                        requestResponseBaseFactory.build(),
                        userEntityList
                ));
    }

    @RequestMapping(value = PATH, method = RequestMethod.POST)
    public ResponseEntity<UserApiNewUserResponse> invoke(@Valid UserApiNewUserRequest request) {
        var result = userRepository.activate(new UserRepositoryActivateNewUserContainer(
                request.getUserNameForm().getFormValue(),
                new UserActivatedDateTime(requestEvent.getRequestEventDateTime().toLocalDateTime())
        ));

        if (result.isInvalid()) {
            throw result.getError();
        }
        var userEntityActive = result.get();

        return ResponseEntity
                .ok()
                .body(UserApiNewUserResponse.build(
                        requestResponseBaseFactory.build(),
                        userEntityActive.getUserId()
                ));
    }

    @RequestMapping(value = PATH + "/greet", method = RequestMethod.POST)
    public ResponseEntity<UserApiGreetResponse> invoke(@Valid UserApiGreetRequest request) {
        apiRequestLogger.info("invoke() called" + request.toString());

        var userEntityOption = userRepository.find(request.getUserIdForm().getFormValue());

        if (userEntityOption.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(UserApiGreetResponse.build(
                            requestResponseBaseFactory.build(),
                            "User not found.",
                            "",
                            ""
                    ));
        }

        var userEntity = userEntityOption.get();

        return ResponseEntity
                .ok()
                .body(UserApiGreetResponse.build(
                        requestResponseBaseFactory.build(),
                        "Hello, " + userEntity.getUserName().getApiValue() + "!",
                        userEntity.getUserId().getApiValue(),
                        userEntity.getUserStatus().getApiValue()
                ));
    }
}
