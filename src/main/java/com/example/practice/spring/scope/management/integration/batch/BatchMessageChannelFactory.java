package com.example.practice.spring.scope.management.integration.batch;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BatchMessageChannelFactory {
    private final BatchMessageChannelLoggingInterceptor batchMessageChannelLoggingInterceptor;

    public MessageChannel build(Class<?> clazz) {
        var channel = new RendezvousChannel();
        channel.setDatatypes(clazz);
        channel.addInterceptor(batchMessageChannelLoggingInterceptor);

        return channel;
    }
}
