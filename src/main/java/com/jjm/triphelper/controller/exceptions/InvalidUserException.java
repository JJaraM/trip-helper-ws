package com.jjm.triphelper.controller.exceptions;

public class InvalidUserException extends RuntimeException {

    private String username;

    public InvalidUserException(String username) {
        super("Does not found any user with the id " + username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}