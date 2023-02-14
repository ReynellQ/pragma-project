package com.pragma.userservice.domain.api;

public interface IAuth {
    String encryptPassword(String rawPassword);
    boolean checkPasswordWithDatabase(String rawPassword, String databasePassword);
}
