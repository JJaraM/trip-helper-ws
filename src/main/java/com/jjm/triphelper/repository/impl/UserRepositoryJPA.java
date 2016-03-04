package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.entity.jpa.QUserJPA;
import com.jjm.triphelper.entity.jpa.UserJPA;
import com.jjm.triphelper.repository.CacheRepository;
import com.jjm.triphelper.repository.UserRepository;

public interface UserRepositoryJPA extends CacheRepository<UserJPA, Integer>, UserRepository {

    @Override
    default User fetchByUsernameAndPassword(String username, String password) {
        QUserJPA DEF = QUserJPA.userJPA;
        return findOne(DEF.username.eq(username).and(DEF.password.eq(password)));
    }

    @Override
    default User save(String username, String password) {
        User user = new UserJPA();
        user.setUsername(username);
        user.setPassword(password);
        save((UserJPA)user);
        return user;
    }
}