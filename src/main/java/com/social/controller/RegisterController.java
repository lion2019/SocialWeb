package com.social.controller;

import com.social.domain.RegisterRequest;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.RegisterService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

@MultipartConfig
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends BaseController {

	private static final long serialVersionUID = 1L;

	private RegisterService registerService = new RegisterService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		System.out.println(getServletName());

		try {
			RegisterRequest registerRequest = reqParam2Bean(req, RegisterRequest.class)
					.orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

			registerRequest.valid();

			User user = registerRequest.convert2User();

			if(!registerService.addUser(user)) {
				throw new BaseException(ResponseEnum.insert_error);
			}
			
//			user.ofNullable(null)
			
//			System.out.println("name["+user.map(User::getName).orElse("name is empty"));
			
			
			// FIXME ServletException 內容可調使用 enum  
//			User output = user.filter(registerService::addUser).orElseThrow(()->new ServletException("user add error!!"));
			
//			user.ifPresent(registerService::addUser);
//			req.setAttribute("userInfo", user);
			
			resp.setContentType("application/json;");
			resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
			
			JSONObject output = JSONObject.fromObject(user);
			resp.getWriter().println(output);
		} catch (InstantiationException | IllegalAccessException | IOException | ServletException
				| IntrospectionException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
