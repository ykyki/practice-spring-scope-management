package com.example.practice.spring.scope.management.integration.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
class BatchMessageChannelLoggingInterceptor implements ChannelInterceptor {
    // TODO use canonical logger
    private final static Logger logger = LoggerFactory.getLogger(BatchMessageChannelLoggingInterceptor.class);

    @Override
    public Message<?> preSend(
            @NonNull Message<?> message,
            @NonNull MessageChannel channel
    ) {
        logger.info(String.format(
                "Message preSend. messageId: %s, timestamp: %s, channel: %s",
                message.getHeaders().getId(),
                message.getHeaders().getTimestamp(),
                channel.getClass().getSimpleName()
        ));

        return message;
    }

    @Override
    public void postSend(
            @NonNull Message<?> message,
            @NonNull MessageChannel channel,
            boolean sent
    ) {
        logger.info(String.format(
                "Message postSend. messageId: %s, timestamp: %s, sent: %s",
                message.getHeaders().getId(),
                message.getHeaders().getTimestamp(),
                sent
        ));
    }

    @Override
    public void afterSendCompletion(
            @NonNull Message<?> message,
            @NonNull MessageChannel channel,
            boolean sent,
            @Nullable Exception ex
    ) {
        logger.info(String.format(
                "Message afterSendCompletion. messageId: %s, timestamp: %s, sent: %s, exception: %s",
                message.getHeaders().getId(),
                message.getHeaders().getTimestamp(),
                sent,
                ex
        ));
    }

    @Override
    public boolean preReceive(
            @NonNull MessageChannel channel
    ) {
        logger.info(String.format(
                "Message preReceive. channel: %s",
                channel.getClass().getSimpleName()
        ));

        return true;
    }

    @Override
    public Message<?> postReceive(
            @NonNull Message<?> message,
            @NonNull MessageChannel channel
    ) {
        logger.info(String.format(
                "Message postReceive. messageId: %s, timestamp: %s",
                message.getHeaders().getId(),
                message.getHeaders().getTimestamp()
        ));

        return message;
    }

    @Override
    public void afterReceiveCompletion(
            @Nullable Message<?> message,
            @NonNull MessageChannel channel,
            @Nullable Exception ex
    ) {
        logger.info(String.format(
                "Message afterReceiveCompletion. messageId: %s, timestamp: %s, exception: %s",
                message == null ? "null" : message.getHeaders().getId(),
                message == null ? "null" : message.getHeaders().getTimestamp(),
                ex
        ));
    }
}
