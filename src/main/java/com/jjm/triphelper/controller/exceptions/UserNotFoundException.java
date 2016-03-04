/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.controller.exceptions;

/**
 * The {@code UserNotFoundException} represents the exception when there is not user
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public class UserNotFoundException extends RuntimeException {

    private final String username;

    public UserNotFoundException(String username) {
        super("The user " + username + " does not exist");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
