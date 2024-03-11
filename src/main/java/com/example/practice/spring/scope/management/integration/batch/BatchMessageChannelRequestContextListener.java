package com.example.practice.spring.scope.management.integration.batch;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequestEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;

@Component
public class BatchMessageChannelRequestContextListener {
    private final RequestContextListener requestContextListener = new RequestContextListener();

    private final ServletContext servletContext = new BatchMessageChannelDummyServletContext();

    public ServletRequestEvent initialize() {
        var servletRequestEvent = new ServletRequestEvent(
                servletContext,
                new BatchMessageChannelDummyHttpServletRequest(servletContext)
        );

        requestContextListener.requestInitialized(servletRequestEvent);

        return servletRequestEvent;
    }

    public void destroy(ServletRequestEvent servletRequestEvent) {
        requestContextListener.requestDestroyed(servletRequestEvent);
    }
}
