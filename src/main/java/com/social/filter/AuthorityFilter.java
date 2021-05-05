package com.social.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 攔截器，驗証權限
 * Servlet Filter implementation class AuthorityFilter
 */
@WebFilter(urlPatterns = {"*.do","*.jsp"})
public class AuthorityFilter implements Filter {

	/** 不需登入即可瀏覽的頁面 */
	private String[] excludeUrlPath = {"/index.jsp","/login.*","/register.*","/logout.*"};

    /**
     * Default constructor.
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * TODO 驗証權限
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;

		System.out.println("getContextPath:"+req.getContextPath());
		// 判斷需要驗証權限的功能 (url)
		if(!isExcludeUrl(req)) {

			// 驗証是否登入
			if(!isLogin(req)) {
				// 是否要先記錄當下請求的url在session?? 若在登入成功後即直接導頁過去
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
//				req.getRequestDispatcher("/login.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * 判斷不用登入即可瀏覽的 url
	 * @param req
	 * @return
	 */
	private boolean isExcludeUrl(HttpServletRequest req) {
		String servletPath = req.getServletPath();
		System.out.println("servletPath:"+servletPath);
		return Arrays.stream(excludeUrlPath).anyMatch(k -> servletPath.matches(k));
	}

	private boolean isLogin(HttpServletRequest req) {
		Object userInfo = req.getSession().getAttribute("userInfo");
		return userInfo != null;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
