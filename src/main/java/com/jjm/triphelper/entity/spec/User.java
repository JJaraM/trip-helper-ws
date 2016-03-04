/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

/**
 * The {@link User} represents the specification of user
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface User extends SingularId {

    String getUsername();
    void setUsername(String username);

    String getPassword();
    void setPassword(String password);
}