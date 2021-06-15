package com.social.test;

import com.social.model.domain.User;
import com.social.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginServiceTest {

    private LoginService loginService = new LoginService();

    @Test
    void checkPasswordTest1(){
        User userRequest = new User();
        userRequest.setPassword("1234");
        User userDB = new User();
        userDB.setPassword("1234");

        // 因密碼相同，此方法應該會返回 true 才是正確
        Assertions.assertTrue(loginService.checkPassword(userRequest, userDB));
    }

    @Test
    void checkPasswordTest2(){
        User userRequest = new User();
        userRequest.setPassword("1234");
        User userDB = new User();
        userDB.setPassword("4321");

        // 因密碼不同，此方法應該會返回 false 才是正確
        Assertions.assertFalse(loginService.checkPassword(userRequest, userDB));
    }
}
