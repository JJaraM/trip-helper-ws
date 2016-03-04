/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.UserNotFoundException;
import com.jjm.triphelper.domain.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The {@code ErrorHandlerAdvice} represents class that manage the all exceptions in the controllers
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
@ControllerAdvice
public class ErrorHandlerAdvice {

    /**
     * Catch the exception to user not found
     * @param ex with the exception message
     * @return response with the error message
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorModel(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
