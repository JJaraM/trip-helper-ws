package com.jjm.triphelper.service;

public interface CryptoService {

    String PASSWORD_KEY = "PASSWORD";
    String USER_ID = "USER_ID";
    String TRIP_ID = "TRIP_ID";
    String PLACE_ID = "PLACE_ID";

    String encrypt(String plainText, String key);
    String decrypt(String encryptedText, String key);
}
