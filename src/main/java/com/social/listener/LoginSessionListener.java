package com.social.listener;

import com.social.domain.OnlineUser;
import com.social.domain.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

/**
 * 監聽器
 * 記錄登入的使用者
 */
@WebListener
public class LoginSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        User userInfo = (User) httpSessionEvent.getSession().getAttribute("userInfo");

        List<OnlineUser> sessionList = (List<OnlineUser>)httpSessionEvent.getSession()
                .getServletContext().getAttribute("onlineUser");

        sessionList.removeIf(o -> o.getNickname().equals(userInfo.getNickname()));
    }
}
