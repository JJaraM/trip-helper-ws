package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.User;

public interface UserRepository {
    User fetchByUsernameAndPassword(String username, String password);
    User save(String username, String password);
}