package com.social.test;

import com.social.model.domain.User;

public class UserTest2 {

    public static void main(String[] args) {
        System.out.println("main方法測試:使用者註冊測試");
        User user = new User();
        user.setNickname("暱名d");
        user.setEmail("d@abc.com");
        user.setGender('F');
        user.setName("會員d");
        user.setPassword("1");
        System.out.println("取得使用者資料 : " + user.toString());
    }
}
