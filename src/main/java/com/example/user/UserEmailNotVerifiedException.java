package com.example.user;

public class UserEmailNotVerifiedException extends Exception {
    public UserEmailNotVerifiedException(String message) {
        super(message);
    }
}
