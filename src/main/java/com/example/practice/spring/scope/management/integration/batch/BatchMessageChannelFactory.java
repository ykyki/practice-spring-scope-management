package com.example.practice.spring.scope.management.integration.batch;

import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class BatchMessageChannelFactory {

    public MessageChannel build() {
        return new QueueChannel();
    }
}
