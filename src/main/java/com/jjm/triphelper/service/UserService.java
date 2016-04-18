package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.User;

public interface UserService {
    User signIn(String email, String password);
    User signUp(String email, String name, String password);
    User findById(String userId);
    User findByEmail(String email);
}
