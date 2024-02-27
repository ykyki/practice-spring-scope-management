package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserGenerateBatchMessageHandler {
    private final UserGenerateBatch userGenerateBatch;

    @ServiceActivator(inputChannel = UserGenerateBatchMessageChannel.NAME)
    public void handle(Message<UserGenerateBatchMessageContainer> message) {
        // TODO use requestLogger
        LoggerFactory.getLogger("UserGenerateBatchMessageHandler").info("messageId: " + message.getHeaders().getId());
        LoggerFactory.getLogger("UserGenerateBatchMessageHandler").info("timestamp: " + message.getHeaders().getTimestamp());

        userGenerateBatch.execute(message.getPayload());
    }
}
