/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.controller;

import com.jjm.triphelper.controller.exceptions.UserNotFoundException;
import com.jjm.triphelper.entity.dto.UserDTO;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.dto.UserRepositoryDTO;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping(value = "api/user", produces = { MediaType.APPLICATION_JSON_VALUE })
public class UserController {

    @Resource private UserService userService;
    @Resource private UserRepositoryDTO userRepositoryDTO;
    @Resource private CryptoService cryptoService;

    @ApiOperation(value = "Sign In", notes = "<p>Sign In into the application</p>")
    @RequestMapping(value = "signIn", method = RequestMethod.GET )
    public ResponseEntity<UserDTO> signIn(@RequestParam(value = "username", required = true) final String username,
                                          @RequestParam(value = "password", required = true) final String password) {
        User user = userService.signIn(username, password);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        return new ResponseEntity<>(userRepositoryDTO.find(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Sign Up", notes = "<p>Sign Up into the application</p>")
    @RequestMapping(value = "signUp", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> signUp(@RequestParam(value = "username", required = true) final String username,
                                          @RequestParam(value = "password", required = true) final String password){
        User user = userService.signUp(username, password);
        return new ResponseEntity<>(userRepositoryDTO.find(user), HttpStatus.CREATED);
    }
}
