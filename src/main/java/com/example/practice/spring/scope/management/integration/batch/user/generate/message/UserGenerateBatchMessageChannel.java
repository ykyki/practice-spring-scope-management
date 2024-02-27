package com.example.practice.spring.scope.management.integration.batch.user.generate.message;

import com.example.practice.spring.scope.management.integration.batch.BatchMessageChannelFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserGenerateBatchMessageChannel {
    public static final String NAME = "UserGenerateBatchMessageChannel";

    private final BatchMessageChannelFactory batchMessageChannelFactory;

    @Bean(name = NAME)
    public MessageChannel make() {
        return batchMessageChannelFactory.build();
    }
}
