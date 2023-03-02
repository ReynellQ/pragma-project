package com.pragma.userservice.infrastructure.exception;

import com.pragma.userservice.domain.exception.ApiRestException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiRestException {
    public UserNotFoundException() {
        this("The users doesn't exists.");
    }

    public UserNotFoundException(Object msg) {
        this.msg = msg;
        httpStatus = HttpStatus.NOT_FOUND;
    }
}
