package com.social.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.model.domain.OnlineUser;
import com.social.model.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.LoginService;

@WebServlet(urlPatterns = "/login.do")
public class Login extends BaseController {
    private static final long serialVersionUID = 1L;

    private LoginService loginService = new LoginService();

    //登入
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = reqParam2Bean(request, User.class)
                    .orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));
            //由LoginService驗證密碼後將userInfo 存入 session
            User loginUser = loginService.login(user, request);

            response.sendRedirect(request.getContextPath() + "/main.jsp");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IOException
                | ServletException | IntrospectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            request.setAttribute("errorMsg", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

}
