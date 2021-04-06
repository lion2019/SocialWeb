package com.social.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BaseController() {
		super();
	}

	/**
	 * FIXME 未驗証 param 輸入的值
	 * 若 request parameter key 和 bean field name 相同時，則可使用
	 * @param <T>
	 * @param req
	 * @param cls
	 * @return 
	 */
	protected <T> Optional<T> reqParam2Bean(HttpServletRequest req, Class<T> cls) throws IOException, ServletException,
			IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
		// 如果沒有 param 一樣會有 Enumeration 物件，非 null
		Enumeration<String> parameterNames = req.getParameterNames();

		T obj = parameterNames.hasMoreElements() ? cls.newInstance() : null;

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			
			PropertyDescriptor propertyDescriptor;
			propertyDescriptor = new PropertyDescriptor(key, cls);
			Method writeMethod = propertyDescriptor.getWriteMethod();
			writeMethod.invoke(obj, req.getParameter(key));
		}
		
		System.out.println(obj);
		
		return Optional.ofNullable(obj);
	}


	
//	// FIXME 動態驗証?
//	private boolean checkParam(HttpServletRequest req) {
//		
//	}
}