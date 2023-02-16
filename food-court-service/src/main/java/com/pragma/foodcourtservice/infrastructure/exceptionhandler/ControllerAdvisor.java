package com.pragma.foodcourtservice.infrastructure.exceptionhandler;

import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.infrastructure.exception.UserDoesntExistsException;
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


    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectDataException(
            IncorrectDataException ignoredIncorrectDataException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "The user provided incorrect data.") );
    }

}

