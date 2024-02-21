package com.example.practice.spring.scope.management.mvc.api.wait;

import com.example.practice.spring.scope.management.domain.wait.JustWaitService;
import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBaseFactory;
import com.example.practice.spring.scope.management.mvc.api.wait.parallel.WaitApiParallelResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
class WaitApi {
    private final JustWaitService justWaitService;

    private final RequestResponseBaseFactory requestResponseBaseFactory;

    private final static String PATH = "/api/wait";

    @RequestMapping(value = PATH + "/parallel", method = RequestMethod.GET)
    public ResponseEntity<WaitApiParallelResponse> invoke() {
        var results = justWaitService.waitParallel();

        return ResponseEntity
                .ok()
                .body(WaitApiParallelResponse.build(
                        requestResponseBaseFactory.build(),
                        results
                ));
    }
}
