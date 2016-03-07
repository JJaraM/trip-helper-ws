package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.Serializer;
import com.jjm.triphelper.entity.jpa.UserJPA;
import com.jjm.triphelper.entity.jpa.interceptor.UserInterceptor;

@Chameleon(type = UserJPA.class)
public class UserDTO {

    @Serializer(value = UserInterceptor.class) private String id;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
