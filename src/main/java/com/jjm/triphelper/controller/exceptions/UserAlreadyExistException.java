package com.jjm.triphelper.controller.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    private final String email;

    public UserAlreadyExistException(String email) {
        super("The user " + email + " already exist.");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
