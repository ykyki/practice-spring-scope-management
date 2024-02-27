package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = UserGenerateBatchMessageChannel.NAME)
public interface UserGenerateBatchMessageGateway {
    void send(UserGenerateBatchMessageContainer container);
}
