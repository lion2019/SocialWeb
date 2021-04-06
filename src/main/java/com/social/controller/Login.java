package com.social.controller;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.LoginService;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/login")
public class Login extends BaseController {
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = reqParam2Bean(request, User.class)
					.orElseThrow(()->new BaseException(ResponseEnum.parameter_empty));
			
			Optional<User> user2 = loginService.login(user);
			
			response.setContentType("application/json;");
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			
			JSONObject output = JSONObject.fromObject(user2.orElseThrow(()-> new BaseException(ResponseEnum.user_not_found)));
			response.getWriter().println(output);
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | IOException
				| ServletException | IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
