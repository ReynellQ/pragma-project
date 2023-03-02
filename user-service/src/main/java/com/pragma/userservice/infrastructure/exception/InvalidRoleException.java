package com.pragma.userservice.infrastructure.exception;

import com.pragma.userservice.domain.exception.ApiRestException;
import org.springframework.http.HttpStatus;

public class InvalidRoleException extends ApiRestException {
    public InvalidRoleException() {
        this("The role assigned is invalid.");
    }

    public InvalidRoleException(Object msg) {
        this.msg = msg;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
