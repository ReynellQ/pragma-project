package com.pragma.userservice.domain.api;

public interface IPersonaChecker {
    boolean emailChecker(String email);
    boolean phoneChecker(String phone);
}
