package com.pragma.userservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class that represents all the possible exceptions during a request to the API, including a default server
 * exception in case the exception wasn't caught.
 */
public abstract class BadApiRequestException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;
    private List<Map.Entry<String, String>> fieldErrorWithDescription;

    /**
     * Constructs a new BadApiRequestException. Has the message and the HttpStatus.
     * @param message The message to be shown to the API
     * @param httpStatus The HTTP Status Code to be shown to the API
     */
    public BadApiRequestException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.fieldErrorWithDescription = new ArrayList<>();
    }


    /**
     * Constructs a new BadApiRequestException. Has the message and the HttpStatus.
     * @param message The message to be shown to the API
     * @param httpStatus The HTTP Status Code to be shown to the API
     * @param fieldErrorWithDescription The field that has error and the reason.
     */
    public BadApiRequestException(String message, HttpStatus httpStatus, List<Map.Entry<String, String>> fieldErrorWithDescription) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.fieldErrorWithDescription = fieldErrorWithDescription;
    }

    public ResponseEntity<Map<String, Object>> responseExceptionToClient(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        Map<String, String> errors =  new HashMap<>();
        fieldErrorWithDescription.forEach( (error)-> errors.put(error.getKey(), error.getValue()));
        if(errors.size() != 0){
            response.put("errors", errors);
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
