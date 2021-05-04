package com.social.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.LoginService;

@WebServlet(urlPatterns = "/login.do")
public class Login extends BaseController {
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = reqParam2Bean(request, User.class)
					.orElseThrow(()->new BaseException(ResponseEnum.parameter_empty));
			
			loginService.login(user, request);
			
//			JSONObject output = JSONObject.fromObject(userInfo);
			
//			HttpSession session = request.getSession();
//			session.setAttribute("userInfo", output);

//			response.setContentType("application/json;");
//			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			
//			response.getWriter().println(output);
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | IOException
				| ServletException | IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
