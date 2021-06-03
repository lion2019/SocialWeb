package com.social.test;

import com.social.model.domain.User;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void registerTest(){
        System.out.println("單元測試:使用者註冊測試");
        User user = new User();
        user.setNickname("暱名d");
        user.setEmail("d@abc.com");
        user.setGender('F');
        user.setName("會員d");
        user.setPassword("1");
        System.out.println("取得使用者資料 : " + user.toString());

    }
}
