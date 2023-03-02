package com.pragma.userservice.infrastructure.exception;

import com.pragma.userservice.domain.exception.ApiRestException;
import org.springframework.http.HttpStatus;

public class UserConflictForEmailException extends ApiRestException {
    public UserConflictForEmailException() {
        this("A user with this email already exists.");
    }

    public UserConflictForEmailException(Object msg) {
        this.httpStatus = HttpStatus.CONFLICT;
        this.msg = msg;
    }
}
