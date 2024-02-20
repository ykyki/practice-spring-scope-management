package com.example.practice.spring.scope.management.mvc.handler.exception;

import com.example.practice.spring.scope.management.mvc.api.util.template.RequestResponseBaseFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final RequestResponseBaseFactory requestResponseBaseFactory;

    @ExceptionHandler
    public ResponseEntity<Object> handleExceptionDefault(
            Exception ex,
            WebRequest request
    ) {
        return handleExceptionInternal(
                ex,
                null,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        var builder = new ApiExceptionResponseBuilder(requestResponseBaseFactory.build())
                .setMessage("Method argument not valid");

        ex.getBindingResult().getGlobalErrors().forEach(
                error -> builder.addDetail(error.getObjectName(), error.getDefaultMessage())
        );
        ex.getBindingResult().getFieldErrors().forEach(
                error -> builder.addDetail(error.getField(), error.getDefaultMessage())
        );

        return handleExceptionInternal(
                ex,
                builder.build(),
                headers,
                status,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {
        if (!(body instanceof ApiExceptionResponse)) {
            body = new ApiExceptionResponseBuilder(requestResponseBaseFactory.build())
                    .setMessage(ex.getClass().getSimpleName())
                    .build();
        }

        return super.handleExceptionInternal(
                ex,
                body,
                headers,
                statusCode,
                request
        );
    }
}
