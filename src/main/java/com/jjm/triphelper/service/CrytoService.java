package com.jjm.triphelper.service;

/**
 * Created by jjara on 2/19/2016.
 */
public interface CrytoService {

    String encrypt(String plainText, String key);
    String decrypt(String encryptedText, String key);
}
