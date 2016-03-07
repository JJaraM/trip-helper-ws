package com.jjm.triphelper.controller.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    private final String username;

    public UserAlreadyExistException(String username) {
        super("The user " + username + " already exist.");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
