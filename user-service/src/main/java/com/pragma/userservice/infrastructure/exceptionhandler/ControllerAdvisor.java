package com.pragma.userservice.infrastructure.exceptionhandler;

import com.pragma.userservice.domain.exception.IncorrectCredentialsException;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.infrastructure.exception.UserDoesntExistsException;
import com.pragma.userservice.infrastructure.exception.UserWithEmailAlreadyExistsException;
import com.pragma.userservice.infrastructure.exception.UserWithIDAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(UserDoesntExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserDoesntExistsException(
            UserDoesntExistsException ignoredUserDoesntExistsException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "The users doesn't exists.") );
    }

    @ExceptionHandler(UserWithEmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserWithEmailAlreadyExistsException(
            UserWithEmailAlreadyExistsException ignoredUserWithEmailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "A user with this email already exists.") );
    }

    @ExceptionHandler(UserWithIDAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleUserWithIDAlreadyExistsException(
            UserWithIDAlreadyExistsException ignoredUserWithIDAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "A user with this id already exists.") );
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectDataException(
            IncorrectDataException ignoredIncorrectDataException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The user provided incorrect data.") );
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectCredentialsException(
            IncorrectCredentialsException ignoredIncorrectCredentialsException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, "Cannot login.") );
    }

}

