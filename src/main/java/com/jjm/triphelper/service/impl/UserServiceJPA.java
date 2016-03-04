package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.UserRepository;
import com.jjm.triphelper.service.CrytoService;
import com.jjm.triphelper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceJPA implements UserService {

    @Resource private UserRepository userRepository;
    @Resource private CrytoService crytoService;

    @Override
    public User signIn(String username, String password) {
        return userRepository.fetchByUsernameAndPassword(username, crytoService.encrypt(password, "PASSWORD"));
    }
}
