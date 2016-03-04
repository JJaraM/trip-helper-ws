/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.domain;

/**
 * The {@link ErrorModel} class represents error model to return when there is an exception
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public class ErrorModel {

    private String message;

    public ErrorModel(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
