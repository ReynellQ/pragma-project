package com.pragma.userservice.infrastructure.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiRestExceptionResponse {
    HttpStatus status;
    String message;

    public ApiRestExceptionResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
