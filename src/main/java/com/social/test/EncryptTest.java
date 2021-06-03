package com.social.test;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EncryptTest {

    private static BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();;
    private String pw1 = "1";

    @BeforeAll
    static void init(){
        //設置加密密鑰
        basicTextEncryptor.setPassword("MySalt");
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void init1(){
        System.out.println("BeforeEach");
    }

    @Test
    void test(){
        String encrypt = basicTextEncryptor.encrypt(pw1);
        System.out.println(encrypt);
        Assertions.assertEquals("1", basicTextEncryptor.decrypt(encrypt));
    }
    @Test
    void test1(){
        String encrypt = basicTextEncryptor.encrypt(pw1);
        System.out.println(encrypt);
        Assertions.assertEquals("1", basicTextEncryptor.decrypt(encrypt));
    }
}
