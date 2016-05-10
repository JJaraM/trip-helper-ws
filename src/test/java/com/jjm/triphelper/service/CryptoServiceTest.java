package com.jjm.triphelper.service;

import com.jjm.triphelper.controller.AbstractIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;

public class CryptoServiceTest extends AbstractIntegrationTest {

    @Resource private CryptoService cryptoService;

    @Test
    public void encrypt() {
        Assert.assertEquals("wNh9/ECqUKDsu7kBQH0FYQ==", cryptoService.encrypt("text", "key"));
    }

    @Test
    public void decrypt() {
        Assert.assertEquals("text", cryptoService.decrypt("wNh9/ECqUKDsu7kBQH0FYQ==", "key"));
    }
}
