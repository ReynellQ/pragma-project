package com.pragma.userservice.domain.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception that represents bad data supplied in the model layer.
 */
public class IncorrectDataException extends ApiRestException {
    public IncorrectDataException(){
        this("The user provided incorrect data");
    }

    public IncorrectDataException(Object msg) {
        this.msg = msg;
        httpStatus = HttpStatus.BAD_REQUEST;
    }
}
