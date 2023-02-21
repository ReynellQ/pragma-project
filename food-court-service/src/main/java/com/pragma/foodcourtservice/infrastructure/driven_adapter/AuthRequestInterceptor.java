package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestInterceptor implements RequestInterceptor {
    private String authHeader;
    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", authHeader);
    }

    public void setAuthHeader(String authHeader){
        this.authHeader = authHeader;
    }
}
