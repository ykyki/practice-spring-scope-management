package com.example.practice.spring.scope.management.mvc;

import com.example.practice.spring.scope.management.mvc.handler.interceptor.RequestHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WebMvcConfiguration implements WebMvcConfigurer {
    private RequestHandlerInterceptor requestHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestHandlerInterceptor);
    }
}
