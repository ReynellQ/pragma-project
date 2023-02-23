package com.pragma.foodcourtservice.infrastructure.configuration.feign;

import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import feign.Feign;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {
    private final String USER_SERVICE_URL = "http://user-service/";

    private final Map<String, Exception> exceptions = Map.of(
            USER_SERVICE_URL+"user/save/employee", new IncorrectDataException()
    );
    /**
     * Implement this method in order to decode an HTTP {@link Response} when
     * {@link Response#status()} is not in the 2xx range. Please raise application-specific exceptions
     * where possible. If your exception is retryable, wrap or subclass {@link RetryableException}
     *
     * @param methodKey {@link Feign#configKey} of the java method that invoked the request. ex.
     *                  {@code IAM#getUser()}
     * @param response  HTTP response where {@link Response#status() status} is greater than or equal
     *                  to {@code 300}.
     * @return Exception IOException, if there was a network error reading the response or an
     * application-specific exception decoded by the implementation. If the throwable is
     * retryable, it should be wrapped, or a subtype of {@link RetryableException}
     */
    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        return exceptions.get(requestUrl);
    }
}
