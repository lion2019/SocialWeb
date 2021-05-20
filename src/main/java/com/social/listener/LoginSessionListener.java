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

//    private Map<String, OnlineUser> sessionMap = new ConcurrentHashMap();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        User userInfo = (User) httpSessionEvent.getSession().getAttribute("userInfo");
        System.out.println("sessionCreated...["+userInfo);
//        OnlineUser onlineUser = new OnlineUser();
//        onlineUser.setLoginDateTime(LocalDateTime.now());
//        onlineUser.setNickName(userInfo.getNickname());
//
//        sessionMap.put("", )
//        httpSessionEvent.getSession().getServletContext().setAttribute("");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("sessionDestroyed...");
        User userInfo = (User) httpSessionEvent.getSession().getAttribute("userInfo");

        List<OnlineUser> sessionList = (List<OnlineUser>)httpSessionEvent.getSession()
                .getServletContext().getAttribute("onlineUser");
        System.out.println(userInfo);
        System.out.println(sessionList);

//        for(OnlineUser onlineUser:sessionList){
//            if(onlineUser.getNickname().equals(userInfo.getNickname())){
//                sessionList.remove(onlineUser);
//            }
//        }

        sessionList.removeIf(o -> o.getNickname().equals(userInfo.getNickname()));
        System.out.println(sessionList);
    }
}
