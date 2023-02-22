package com.pragma.foodcourtservice.infrastructure.exceptionhandler;

import com.pragma.foodcourtservice.domain.exception.ForbiddenUpdateException;
import com.pragma.foodcourtservice.domain.exception.NotAllowedRestaurantException;
import com.pragma.foodcourtservice.domain.exception.NotAnOwnerException;
import com.pragma.foodcourtservice.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    private static final Map<Class, ApiRestExceptionResponse> messages = Map.ofEntries(
            Map.entry(RestaurantNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The restaurant doesn't exists.")),
            Map.entry(NotAnOwnerException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The owner supplied hasn't the role of Owner.")),
            Map.entry(ForbiddenUpdateException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "Update this data is forbidden.")),
            Map.entry(FoodPlateNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The food plate doesn't exists.")),

            Map.entry(UserNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The users doesn't exists.")),
            Map.entry(CategoryNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The category doesn't exists.")),
            Map.entry(NotAllowedRestaurantException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The current user is not allowed to " +
                            "insert data referent to this restaurant."))
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleApiException(Exception exception) {
        System.out.println(exception.getClass());
        ApiRestExceptionResponse api = messages.get(exception.getClass());
        return ResponseEntity.status(api.status)
                .body(Collections.singletonMap(MESSAGE, api.message));
    }
}

