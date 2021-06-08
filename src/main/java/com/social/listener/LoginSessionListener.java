package com.social.listener;

import com.social.model.domain.OnlineUser;
import com.social.model.domain.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * 監聽器 HttpSession
 * 記錄登入的使用者
 */
@WebListener
public class LoginSessionListener implements HttpSessionListener {
    //第一次請求時tomcat會建立一個session
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }
    //timeOut時tomcat會自動銷毀session，或登出時程式增加session銷毀
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //取得使用者資料，LoginService會存userInfo。
        User userInfo = (User) httpSessionEvent.getSession().getAttribute("userInfo");
        //login登入時會儲存onlineUser，onlineUser資料會存放在list
        List<OnlineUser> sessionList = (List<OnlineUser>)httpSessionEvent.getSession()
                .getServletContext().getAttribute("onlineUser");
        //比對Nickname，不存在時，移除sessionList的onlineUser
        sessionList.removeIf(o -> o.getNickname().equals(userInfo.getNickname()));
    }
}
