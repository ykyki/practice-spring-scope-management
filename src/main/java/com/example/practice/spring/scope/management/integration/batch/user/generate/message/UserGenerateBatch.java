package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import com.example.practice.spring.scope.management.domain.common.sleep.SleepUtilService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserGenerateBatch {
    // private final RequestLogger requestLogger; // TODO

    private final SleepUtilService sleepUtilService;

    public void execute(UserGenerateBatchMessageContainer container) {
        // TODO
        // requestLogger.info("message: (%s)", container.message());
        LoggerFactory.getLogger("UserGenerateBatch").info("message: " + container.message());

        for (int i = 0; i < 5; i++) {
            sleepUtilService.sleep(1000);
            // requestLogger.info("i: (%d)", i);
            LoggerFactory.getLogger("UserGenerateBatch").info("count: " + i);
        }
    }
}
