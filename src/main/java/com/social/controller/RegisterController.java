package com.social.controller;

import com.social.domain.RegisterRequest;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.service.LoginService;
import com.social.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@MultipartConfig
@WebServlet(urlPatterns = {"/register.do"})
public class RegisterController extends BaseController {

	private static final long serialVersionUID = 1L;

	private RegisterService registerService = new RegisterService();
	private LoginService loginService = new LoginService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(getServletName());

		try {
			RegisterRequest registerRequest = reqParam2Bean(req, RegisterRequest.class)
					.orElseThrow(() -> new BaseException(ResponseEnum.parameter_empty));

			registerRequest.valid();

			User user = registerRequest.convert2Entity();

			if(!registerService.addUser(user)) {
				throw new BaseException(ResponseEnum.insert_error);
			}

			loginService.login(user, req);

			resp.sendRedirect(req.getContextPath()+"/main.jsp");
//			user.ofNullable(null)
			
//			System.out.println("name["+user.map(User::getName).orElse("name is empty"));
			
			
			// FIXME ServletException 內容可調使用 enum  
//			User output = user.filter(registerService::addUser).orElseThrow(()->new ServletException("user add error!!"));
			
//			user.ifPresent(registerService::addUser);
//			req.setAttribute("userInfo", user);
			
//			resp.setContentType("application/json;");
//			resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
			
//			JSONObject output = JSONObject.fromObject(user);
//			resp.getWriter().println(output);
		} catch (BaseException e){
			e.printStackTrace();
			String errorMsg = e.getCode() == 23505? "email 已存在!":ResponseEnum.insert_error.getMessage();
			req.setAttribute("errorMsg", errorMsg);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			return;
//			System.err.println("err code:"+e.getCode());
//			System.err.println("err msg:"+e.getMessage());
//			throw e;
		} catch (InstantiationException | IllegalAccessException | IOException
				| IntrospectionException | InvocationTargetException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
