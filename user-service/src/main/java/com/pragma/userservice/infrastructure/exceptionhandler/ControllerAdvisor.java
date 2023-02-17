package com.pragma.userservice.infrastructure.exceptionhandler;

import com.pragma.userservice.domain.exception.IncorrectCredentialsException;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.infrastructure.exception.InvalidRoleException;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import com.pragma.userservice.infrastructure.exception.UserConflictForEmailException;
import com.pragma.userservice.infrastructure.exception.UserConflictForIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            Map.entry(IncorrectCredentialsException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "Cannot login.")),
            Map.entry(InvalidRoleException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The role assigned is invalid."))
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleApiException(Exception exception){
        System.out.println(exception.getClass());
        ApiRestExceptionResponse api = messages.get(exception.getClass());
        return ResponseEntity.status(api.status)
                .body(Collections.singletonMap(MESSAGE, api.message));
    }
}

