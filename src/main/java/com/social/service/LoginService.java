package com.social.service;

import com.social.dao.UserDao;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginService {
    private UserDao userDao = new UserDao();

    /**
     * @param user
     * @param request
     * @return
     * @throws BaseException user.email not found or password error
     */
    public User login(User user, HttpServletRequest request) throws BaseException {
        User userInfo = userDao.findByEmail(user.getEmail())
                .orElseThrow(() -> new BaseException(ResponseEnum.user_not_found));

        if (checkPassword(user, userInfo)) {
            // 密碼驗証成功後將 userInfo 存入 session
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", userInfo);

            return userInfo;
        } else {
            throw new BaseException(ResponseEnum.password_error);
        }
    }


    public boolean checkPassword(User userRequest, User userInfo) {
        return userRequest.getPassword().equals(userInfo.getPassword());
    }
}
