package com.example.practice.spring.scope.management.integration.batch;

import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class BatchMessageChannelFactory {

    public MessageChannel build(Class<?> clazz) {
        var channel = new RendezvousChannel();
        channel.setDatatypes(clazz);

        return channel;
    }
}
