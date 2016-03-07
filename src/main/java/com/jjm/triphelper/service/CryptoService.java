package com.jjm.triphelper.service;

public interface CryptoService {
    String encrypt(String plainText, String key);
    String decrypt(String encryptedText, String key);
}
