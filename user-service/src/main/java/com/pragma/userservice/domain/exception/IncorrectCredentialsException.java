package com.pragma.userservice.domain.exception;

/**
 * Exception that represents an error in the login of a user. Can be incorrect password or non-existing user.
 * Both are managed equal and doesn't tell if the user doesn't exist or the password is incorrect.
 */
public class IncorrectCredentialsException extends RuntimeException {
}
