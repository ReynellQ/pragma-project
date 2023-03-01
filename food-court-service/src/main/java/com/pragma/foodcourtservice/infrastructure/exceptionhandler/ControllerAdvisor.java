package com.pragma.foodcourtservice.infrastructure.exceptionhandler;

import com.pragma.foodcourtservice.domain.exception.*;
import com.pragma.foodcourtservice.domain.exception.NotPendingOrderException;
import com.pragma.foodcourtservice.domain.useCase.InvalidPinException;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
            Map.entry(IncorrectDataException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")),
            Map.entry(UserNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The users doesn't exists.")),
            Map.entry(CategoryNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The category doesn't exists.")),
            Map.entry(NotAllowedRestaurantException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The current user is not allowed to " +
                            "insert data referent to this restaurant.")),
            Map.entry(NotAClientException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "A client is needed.")),
            Map.entry(InvalidOrderException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The order is invalid.")),
            Map.entry(MethodArgumentNotValidException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")
            ),
            Map.entry(MethodArgumentTypeMismatchException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "The user provided incorrect data.")
            ),
            Map.entry(OrderNotFoundException.class,
                    new ApiRestExceptionResponse(HttpStatus.NOT_FOUND, "The order doesn't exist.")
            ),
            Map.entry(NoPlatesException.class,
                    new ApiRestExceptionResponse(HttpStatus.BAD_REQUEST, "There are no plates in the order.")
            ),
            Map.entry(NotPendingOrderException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The order isn't PENDING, can't be assigned.")
            ),
            Map.entry(NotAnEmployeeException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "A employee is needed.")
            ),
            Map.entry(ForbiddenWorkInOrderException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The order is forbidden. It's from other" +
                            "restaurant.")
            ),
            Map.entry(InvalidPinException.class,
                    new ApiRestExceptionResponse(HttpStatus.FORBIDDEN, "The pin code isn't correct or is invalid.")
            )
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleApiException(Exception exception) {
        ApiRestExceptionResponse api = messages.get(exception.getClass());
        return ResponseEntity.status(api.status)
                .body(Collections.singletonMap(MESSAGE, api.message));
    }
}

