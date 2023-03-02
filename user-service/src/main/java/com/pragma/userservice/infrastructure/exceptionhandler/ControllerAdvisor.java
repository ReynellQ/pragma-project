package com.pragma.userservice.infrastructure.exceptionhandler;

import com.pragma.userservice.domain.exception.ApiRestException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "message";

    private static final Map<Class, ApiRestExceptionResponse> messages = Map.ofEntries(
            Map.entry(BadCredentialsException.class,
                    new ApiRestExceptionResponse(HttpStatus.UNAUTHORIZED, "Cannot login.")),
            Map.entry(AccessDeniedException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The current user doesn't have"+
                            " permissions.")),
            Map.entry(JwtException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The session isn't valid or expired.")),
            Map.entry(MethodArgumentNotValidException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")
                    ),
            Map.entry(MethodArgumentTypeMismatchException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")
                    )
    );

    @ExceptionHandler(ApiRestException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiRestException exception){
        HttpStatus httpStatus = exception.getHttpStatus();
        Object msg = exception.getMsg();
        return ResponseEntity.status(httpStatus)
                .body(Collections.singletonMap(MESSAGE, msg));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception){
        ApiRestExceptionResponse api = messages.getOrDefault(exception.getClass(),
        new ApiRestExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "This exception isn't handled."));

        return ResponseEntity.status(api.status)
                .body(Collections.singletonMap(MESSAGE, api.message));
    }
}

