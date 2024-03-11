package com.example.practice.spring.scope.management.integration.batch.core.user.generate.message;

import com.example.practice.spring.scope.management.integration.batch.BatchMessagingGateway;

@BatchMessagingGateway(defaultRequestChannel = UserGenerateBatchMessageChannel.NAME)
public interface UserGenerateBatchMessageGateway {
    void send(UserGenerateBatchMessageContainer container);
}
