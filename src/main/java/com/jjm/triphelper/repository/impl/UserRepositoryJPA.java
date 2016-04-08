package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.entity.jpa.UserJPA;
import com.jjm.triphelper.repository.CacheRepository;
import com.jjm.triphelper.repository.UserRepository;

public interface UserRepositoryJPA extends CacheRepository<UserJPA, Integer>, UserRepository {

    User findByEmailAndPassword(String email, String password);
    User findByEmail(String username);

    @Override
    default User save(String email, String name, String password) {
        UserJPA user = new UserJPA();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        save(user);
        return user;
    }

    @Override
    default User findById(Integer id) {
        return findOne(id);
    }
}
