package com.pragma.foodcourtservice.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class FeignResponseException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public FeignResponseException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
