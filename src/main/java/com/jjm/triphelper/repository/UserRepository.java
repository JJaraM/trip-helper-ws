package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.User;

public interface UserRepository {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User save(String email, String password);
    User findById(Integer id);
}