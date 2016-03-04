package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.service.CrytoService;
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
public class CryptoServiceIml implements CrytoService {

    public static final String PASSWORD_HASH_ALGORITHM = "SHA-256";

    @Override
    public String encrypt(String plainText, String key) {
        try {
            Key aesKeySpec = buildKey(key.toCharArray());
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKeySpec);
            byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            return new Base64().encodeToString(encryptedTextBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                | UnsupportedEncodingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String decrypt(String encryptedText, String key) {
        try {
            Key aesKeySpec = buildKey(key.toCharArray());
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKeySpec);
            byte[] encryptedTextBytes = Base64.decodeBase64(encryptedText);
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            return new String(decryptedTextBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                | UnsupportedEncodingException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private Key buildKey(final char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digester = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM);
        digester.update(String.valueOf(password).getBytes("UTF-8"));
        byte[] key = digester.digest();
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        return spec;
    }
}
