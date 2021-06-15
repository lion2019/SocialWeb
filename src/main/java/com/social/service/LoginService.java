package com.social.service;

import com.social.model.UserDao;
import com.social.model.domain.OnlineUser;
import com.social.model.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 登入服務
 */
public class LoginService {
    private UserDao userDao = new UserDao();

    public User login(User user, HttpServletRequest request) throws BaseException {
        // 從DB取得該帳號的資料
        User userInfo = userDao.findByEmail(user.getEmail())
                .orElseThrow(() -> new BaseException(ResponseEnum.user_not_found));

        if (checkPassword(user, userInfo)) {
            // 密碼驗証成功後將 userInfo 存入 session
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", userInfo);

            addOnlineUserToSessionList(userInfo, request);

            return userInfo;
        } else {
            throw new BaseException(ResponseEnum.password_error);
        }
    }

    /**
     * 檢查密碼是否正確
     */
    public boolean checkPassword(User userRequest, User userInfo) {
        return userRequest.getPassword().equals(userInfo.getPassword());
    }

    /**
     * 將登入的帳號召入線上使用者清單
     * @param userInfo 使用者資訊
     */
    private void addOnlineUserToSessionList(User userInfo, HttpServletRequest request){
        //InitServlet 存入 sessionlist
        List<OnlineUser> sessionList = (List<OnlineUser>) request.getServletContext()
                .getAttribute("onlineUser");

        //判斷o.getNickname()裡都沒有loginUser.getNickname()的暱名就返回true
        //確認該帳號是否已存在線上使用者清單
        boolean noneMatch = sessionList.stream()
                .noneMatch(o -> o.getNickname().equals(userInfo.getNickname()));

        //若線上使用者清單沒有此帳號，則加入
        if(noneMatch){
            OnlineUser onlineUser = new OnlineUser();
            onlineUser.setNickname(userInfo.getNickname());
            onlineUser.setLoginDateTime(LocalDateTime.now());

            sessionList.add(onlineUser);
        }
        System.out.println(sessionList);
    }
}
