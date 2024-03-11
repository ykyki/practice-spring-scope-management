package com.example.practice.spring.scope.management.integration.batch.core.user.generate;

import com.example.practice.spring.scope.management.integration.batch.core.user.generate.message.UserGenerateBatchMessageContainer;
import com.example.practice.spring.scope.management.mvc.logger.RequestLogger;
import com.example.practice.spring.scope.management.util.sleep.SleepUtil;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserGenerateBatch {
    private final RequestLogger requestLogger; // TODO

    private final SleepUtil sleepUtil;

    public void execute(UserGenerateBatchMessageContainer container) {
        // TODO
        requestLogger.info("message: (%s)", container.message());
        LoggerFactory.getLogger("UserGenerateBatch").info("message: " + container.message());

        for (int i = 0; i < 5; i++) {
            sleepUtil.sleep(1000);
            // requestLogger.info("i: (%d)", i);
            LoggerFactory.getLogger("UserGenerateBatch").info("count: " + i);
        }
    }
}
