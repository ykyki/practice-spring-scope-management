package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import com.example.practice.spring.scope.management.integration.batch.BatchMessageChannelServiceActivator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserGenerateBatchMessageHandler {
    private final UserGenerateBatch userGenerateBatch;

    @BatchMessageChannelServiceActivator(inputChannel = UserGenerateBatchMessageChannel.NAME)
    public void handle(Message<UserGenerateBatchMessageContainer> message) {
        userGenerateBatch.execute(message.getPayload());
    }
}
