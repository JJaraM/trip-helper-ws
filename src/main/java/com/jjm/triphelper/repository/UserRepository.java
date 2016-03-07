package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.User;

public interface UserRepository {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User save(String username, String password);
    User findById(Integer id);
}