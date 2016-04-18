package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.service.CryptoService;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceIml implements CryptoService {

    private static final String PASSWORD_HASH_ALGORITHM = "SHA-256";
    private static final String CIPHER_MODE = "AES";
    private static final String CHARSET = "UTF-8";

    @Override
    public String encrypt(String plainText, String key) {
        String encryptText = "";
        try {
            Key aesKeySpec = buildKey(key.toCharArray());
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec);
            byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes(CHARSET));
            encryptText = new Base64().encodeToString(encryptedTextBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        String decryptText = "";
        try {
            Key aesKeySpec = buildKey(key.toCharArray());
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, aesKeySpec);
            byte[] encryptedTextBytes = Base64.decodeBase64(encryptedText);
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            decryptText = new String(decryptedTextBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return decryptText;
    }

    private Key buildKey(final char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digester = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM);
        digester.update(String.valueOf(password).getBytes(CHARSET));
        return new SecretKeySpec(digester.digest(), CIPHER_MODE);
    }
}
