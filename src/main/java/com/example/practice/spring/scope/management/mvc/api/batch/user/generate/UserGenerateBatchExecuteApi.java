package com.example.practice.spring.scope.management.mvc.api.batch.user.generate;

import com.example.practice.spring.scope.management.integration.batch.user.generate.message.UserGenerateBatchMessageContainer;
import com.example.practice.spring.scope.management.integration.batch.user.generate.message.UserGenerateBatchMessageGateway;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBase;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBaseFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserGenerateBatchExecuteApi {
    private final UserGenerateBatchMessageGateway gateway;

    private final RequestResponseBaseFactory requestResponseBaseFactory;

    private final static String PATH = "/api/batch/user/generate";

    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public ResponseEntity<RequestResponseBase> invoke() {
        gateway.send(new UserGenerateBatchMessageContainer("Hello"));

        return ResponseEntity
                .ok()
                .body(requestResponseBaseFactory.build());
    }
}
