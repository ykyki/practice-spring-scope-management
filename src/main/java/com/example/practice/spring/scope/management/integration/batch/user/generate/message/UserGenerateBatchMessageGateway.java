package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import com.example.practice.spring.scope.management.integration.batch.BatchMessagingGateway;

@BatchMessagingGateway(channelName = UserGenerateBatchMessageChannel.NAME)
public interface UserGenerateBatchMessageGateway {
    void send(UserGenerateBatchMessageContainer container);
}
