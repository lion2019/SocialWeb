package com.social.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout.do")
public class Logout extends BaseController {
	private static final long serialVersionUID = 1L;

	/**
	 * 登出
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// 銷毀 session
		session.invalidate();
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
