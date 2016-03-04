/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.User;
import javax.persistence.*;

/**
 * The {@link UserJPA} represents the implementation of JPA to {@link User}
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
@Entity
@Table(name = "Uzer")
public class UserJPA implements User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="user_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    private Integer id;

    @Column(name = "username", nullable = false, length = 20, unique = true) private String username;
    @Column(name = "password", nullable = false, length = 20) private String password;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
