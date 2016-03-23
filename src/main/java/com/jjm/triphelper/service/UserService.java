/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.User;

/**
 * The {@code UserService} represents the common operations to user operations
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface UserService {

    User signIn(String email, String password);

    User signUp(String email, String password);

    User findById(String userId);
}
