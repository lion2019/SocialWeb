package com.social.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.social.datasource.ConnectionPool;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	public void init() throws ServletException {
		try {
			System.out.println("InitServlet.init");
			Properties properties = new Properties();
			
			InputStream is = getServletContext().getResourceAsStream("/WEB-INF/application.properties");
			properties.load(is);
			
			String jdbcUrl = properties.getProperty("jdbcUrl");
			String driver = properties.getProperty("driver-class-name");
			String userName = properties.getProperty("userName");
			String password = properties.getProperty("password");
			
//			String jdbcUrl = config.getInitParameter("jdbcUrl");
//			String driver = config.getInitParameter("driver-class-name");
//			String userName = config.getInitParameter("userName");
//			String password = config.getInitParameter("password");
			
			HikariDataSource hikariDataSource = new HikariDataSource();
			hikariDataSource.setUsername(userName);
			hikariDataSource.setPassword(password);
			hikariDataSource.setJdbcUrl(jdbcUrl);
			hikariDataSource.setDriverClassName(driver);
			ConnectionPool.setDataSource(hikariDataSource);
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
