package com.example.practice.spring.scope.management;

import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    @Override
    @Bean
    public Executor getAsyncExecutor() {
        var executor = new ContextAwarePoolExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(32);
        executor.setQueueCapacity(128);
        executor.setThreadNamePrefix("AsyncTask-");
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // TODO
        return null;
    }

    public static class ContextAwarePoolExecutor extends ThreadPoolTaskExecutor {
        @Override
        public void execute(@NonNull Runnable task) {
            super.execute(new ContextAwareRunnable(
                    task,
                    RequestContextHolder.currentRequestAttributes()
            ));
        }
    }

    @AllArgsConstructor
    public static class ContextAwareRunnable implements Runnable {
        private final Runnable runnable;

        private final RequestAttributes requestAttributes;

        @Override
        public void run() {
            RequestContextHolder.setRequestAttributes(requestAttributes);

            try {
                runnable.run();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}
