package com.social.model.domain;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * 密碼加密用 (未啟用)
 * 需先修改資料庫欄位長度
 * 加密後的字串一定大於 20
 */
public class EncryptorUtils {
    private static BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
    private static final String encryptorPassword = "HwaHsiaUniversityofTechnology";

    static {
        basicTextEncryptor.setPassword(encryptorPassword);
    }

    public static String decrypt(String text){
        return basicTextEncryptor.decrypt(text);
    }
    public static String encrypt(String text){
        return basicTextEncryptor.encrypt(text);
    }
}
