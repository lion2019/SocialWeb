package com.social.controller;

import com.social.datasource.ConnectionPool;
import com.social.domain.OnlineUser;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<OnlineUser> sessionList = new CopyOnWriteArrayList();

	@Override
	public void init() throws ServletException {
		try {
			System.out.println("InitServlet.init");
			Properties properties = new Properties();

			// 讀取 application.properties
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
			properties.load(is);
			
			String jdbcUrl = properties.getProperty("jdbcUrl");
			String driver = properties.getProperty("driver-class-name");
			String userName = properties.getProperty("userName");
			String password = properties.getProperty("password");
			
			// 設定 連線池
			HikariDataSource hikariDataSource = new HikariDataSource();
			hikariDataSource.setUsername(userName);
			hikariDataSource.setPassword(password);
			hikariDataSource.setJdbcUrl(jdbcUrl);
			hikariDataSource.setDriverClassName(driver);
			ConnectionPool.setDataSource(hikariDataSource);

			// 記錄登入的使用者
			getServletContext().setAttribute("onlineUser", sessionList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		HikariDataSource dataSource = (HikariDataSource)ConnectionPool.getDataSource();
		dataSource.close();
		super.destroy();
	}
}
