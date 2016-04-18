package com.jjm.triphelper.service.impl;

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
    public User signIn(String email, String password) {
        return userRepository.findByEmailAndPassword(email, cryptoService.encrypt(password, CryptoService.PASSWORD_KEY));
    }

    @Override
    public User signUp(String email, String name, String password) {
        return userRepository.save(email, name, cryptoService.encrypt(password, CryptoService.PASSWORD_KEY));
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(Integer.valueOf(cryptoService.decrypt(userId, CryptoService.USER_ID)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
