package com.pragma.userservice.infrastructure.exception;

import com.pragma.userservice.domain.exception.ApiRestException;
import org.springframework.http.HttpStatus;

public class UserConflictForIdException extends ApiRestException {
    public UserConflictForIdException() {
        this("A user with this ID already exists.");
    }

    public UserConflictForIdException(Object msg) {
        this.httpStatus = HttpStatus.CONFLICT;
        this.msg = msg;
    }
}
