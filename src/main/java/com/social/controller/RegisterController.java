package com.social.controller;

import com.social.model.domain.RegisterRequest;
import com.social.model.domain.User;
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

	/**
	 * 會員註冊
	 */
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

		} catch (BaseException e){
			e.printStackTrace();
			String errorMsg = e.getCode() == 23505? "email 已存在!":ResponseEnum.insert_error.getMessage();
			req.setAttribute("errorMsg", errorMsg);
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		} catch (InstantiationException | IllegalAccessException | IOException
				| IntrospectionException | InvocationTargetException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
