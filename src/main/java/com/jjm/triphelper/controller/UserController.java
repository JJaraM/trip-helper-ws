/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.UserNotFoundException;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;

/**
 * The {@code UserController} represents the api operations related to user
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public class UserController {

    @Resource private UserService userService;

    @ApiOperation(
            value = "Sign In",
            notes = "<p>Sign In into the application</p>")
    @RequestMapping(value = "signIn", method = RequestMethod.GET )
    public ResponseEntity<User> signIn(
            @RequestParam(value = "username", required = true) final String username,
            @RequestParam(value = "password", required = true) final String password) {
        User user = userService.signIn(username, password);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new ResponseEntity<>(userService.signIn(username, password), HttpStatus.OK);
    }
}
