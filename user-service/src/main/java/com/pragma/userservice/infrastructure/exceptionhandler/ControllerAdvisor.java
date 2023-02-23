package com.pragma.userservice.infrastructure.exceptionhandler;

import com.pragma.userservice.domain.exception.IncorrectCredentialsException;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.infrastructure.exception.*;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "message";

    private static final Map<Class, ApiRestExceptionResponse> messages = Map.ofEntries(
            Map.entry(UserNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The users doesn't exists.")),
            Map.entry(UserConflictForEmailException.class,
                    new ApiRestExceptionResponse(HttpStatus.CONFLICT, "A user with this email already exists.")),
            Map.entry(UserConflictForIdException.class,
                    new ApiRestExceptionResponse(HttpStatus.CONFLICT, "A user with this id already exists.")),
            Map.entry(IncorrectDataException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")),
            Map.entry(BadCredentialsException.class,
                    new ApiRestExceptionResponse(HttpStatus.UNAUTHORIZED, "Cannot login.")),
            Map.entry(InvalidRoleException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The role assigned is invalid.")),
            Map.entry(ForbiddenCreationException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The current user doesn't have " +
                            "permissions to create this new user.")),
            Map.entry(RuntimeException.class,
                    new ApiRestExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Bullshit happened.")),
            Map.entry(AccessDeniedException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The current user doesn't have"+
                            " permissions.")),
            Map.entry(JwtException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The session isn't valid or expired."))
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleApiException(Exception exception){
        System.out.println(exception.getMessage());
        System.out.println(exception.getClass());
        ApiRestExceptionResponse api = messages.get(exception.getClass());

        return ResponseEntity.status(api.status)
                .body(Collections.singletonMap(MESSAGE, api.message));
    }
}

