package com.example.practice.spring.scope.management.integration.batch;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.handler.advice.AbstractRequestHandlerAdvice;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component(BatchMessageChannelRequestAdvice.NAME)
@AllArgsConstructor
class BatchMessageChannelRequestAdvice extends AbstractRequestHandlerAdvice {
    public final static String NAME = "batchMessageChannelRequestAdvice";

    private final BatchServiceRequestContextListener batchServiceRequestContextListener;

    private final static Logger logger = LoggerFactory.getLogger(BatchMessageChannelRequestAdvice.class);

    @Override
    protected Object doInvoke(ExecutionCallback callback, Object target, Message<?> message) {
        logger.info("BatchMessageChannelRequestAdvice doInvoke");

        // set request scope
        var servletRequestEvent = batchServiceRequestContextListener.initialize();

        try {
            return callback.execute();
        } finally {
            // clear request scope
            batchServiceRequestContextListener.destroy(servletRequestEvent);
        }
    }
}
