package com.social.test;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;

public class EncryptTest {

    String pw1 = "1";

    @Test
    public void test(){
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        //設置加密密鑰
        basicTextEncryptor.setPassword("MySalt");

        String encrypt = basicTextEncryptor.encrypt(pw1);
        System.out.println(encrypt);
    }
}
