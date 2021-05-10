package com.social.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.social.domain.Id;
import org.junit.platform.commons.function.Try;

import com.social.datasource.ConnectionPool;

/**
 * 如何在執行前自動取得 connection ?
 * @author 2291
 *
 * @param <T>
 */
public abstract class BaseDao<T> {
	protected DataSource dataSource;
	
	public BaseDao() {
		this.dataSource = ConnectionPool.getDataSource();
	}
	
	/**
	 * TODO 未完成
	 * @param t
	 * @return
	 * @throws RuntimeException
	 */
	public boolean insert(T t) throws SQLException {
//		String tableName = t.getClass().getSimpleName().toLowerCase();
//		// 取得 private fields
//		Field[] fields = t.getClass().getDeclaredFields();
//		
//		String columnStr = Stream.of(fields).map(Field::getName).collect(Collectors.joining(",", "(", ")"));
//		String values = Stream.of(fields).map(o -> "?").collect(Collectors.joining(",", "(", ")"));
//
//		String sql = "insert into " + tableName + columnStr + " values" + values + ";";
//
		String sql = genInsertSql(t);
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			System.out.println(sql);
			setParameter(ps, t);
			return ps.executeUpdate() == 1;
		}
	}
	
	/**
	 * insert all columns 
	 * @param t
	 * @return
	 */
	protected String genInsertSql(T t) {
		String tableName = t.getClass().getSimpleName().toLowerCase();
		// 取得 private fields
		Field[] fields = t.getClass().getDeclaredFields();

		String columnStr = Stream.of(fields).map(Field::getName).collect(Collectors.joining(",", "(", ")"));
		String values = Stream.of(fields).map(o -> "?").collect(Collectors.joining(",", "(", ")"));

		return "insert into " + tableName + columnStr + " values" + values + ";";
	}
	//PropertyDescriptor
	protected void setParameter(PreparedStatement ps, T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		IntStream.range(0, fields.length).forEach(i->{
			try {
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[i].getName(), obj.getClass());
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(obj);
				if(fields[i].isAnnotationPresent(Id.class) || value == null)
					ps.setNull(i+1, java.sql.Types.NULL);
				else
					ps.setObject(i+1, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| IntrospectionException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
//		Stream.of(fields).map(Field::getName).forEach(o -> {
//			try {
//				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(o, obj.getClass());
//				Method method = propertyDescriptor.getReadMethod();
//				Object value = method.invoke(obj);
//				ps.setString(0, value.toString());
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//					| IntrospectionException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});;
	}
	
	protected Optional<T> resultSet2Bean(ResultSet rs, Class<T> cls)
			throws IOException, ServletException, IntrospectionException, InstantiationException,
			IllegalAccessException, InvocationTargetException, SQLException {
		// 如果沒有 param 一樣會有 Enumeration 物件，非 null
//	Enumeration<String> parameterNames = req.getParameterNames();

		T obj = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			String value = rs.getString(f.getName());

			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), cls);
			Method writeMethod = propertyDescriptor.getWriteMethod();


			if (propertyDescriptor.getPropertyType() == Character.class) {
				writeMethod.invoke(obj, value.charAt(0));
//				writeMethod.invoke(obj, req.getParameter(key).charAt(0));
			} else {
				writeMethod.invoke(obj, value);
//				writeMethod.invoke(obj, req.getParameter(key));
			}
		}

		System.out.println(obj);

		return Optional.ofNullable(obj);
	}
}
