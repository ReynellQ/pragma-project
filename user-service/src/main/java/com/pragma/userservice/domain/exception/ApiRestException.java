package com.pragma.userservice.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public class ApiRestException extends RuntimeException{
    protected HttpStatus httpStatus;
    protected Object msg;

    public ApiRestException(){
        this("The server doesn't process this exception.");

    }
    public ApiRestException(Object msg){
        this.msg = msg;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
