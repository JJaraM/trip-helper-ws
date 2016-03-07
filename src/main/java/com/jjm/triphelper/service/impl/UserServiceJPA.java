package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.controller.exceptions.UserAlreadyExistException;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.UserRepository;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceJPA implements UserService {

    @Resource private UserRepository userRepository;
    @Resource private CryptoService cryptoService;

    @Override
    public User signIn(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, cryptoService.encrypt(password, "PASSWORD"));
    }

    @Override
    public User signUp(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistException(username);
        }
        return userRepository.save(username, cryptoService.encrypt(password, "PASSWORD"));
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(Integer.valueOf(cryptoService.decrypt(userId, "USER_ID")));
    }
}
