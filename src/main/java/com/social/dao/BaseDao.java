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
	 * insert 全部欄位(屬性)
	 * @param t
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
	 * generar insert SQL (all columns)
	 * @param t
	 * @return
	 */
	protected String genInsertSql(T t) {
		// 取得 class name
		String tableName = t.getClass().getSimpleName().toLowerCase();

		/*
		返回一個Field對像數組，該數組反映由該Class對象表示的類或接口聲明的所有字段。
		這包括公共，受保護，默認（程序包）訪問和私有字段，但不包括繼承的字段。
		 */
		Field[] fields = t.getClass().getDeclaredFields();

		// 依照屬性名組成欄位名字串 e.g. (column1,column2,column3,...)
		String columnStr = Stream.of(fields).map(Field::getName).collect(Collectors.joining(",", "(", ")"));
		// 依照屬性名組成動態SQL(?的部份)  e.g. (?,?,?,...)
		String values = Stream.of(fields).map(o -> "?").collect(Collectors.joining(",", "(", ")"));

		// 返回 insert into (a,b,c) values(?,?,?)
		return "insert into " + tableName + columnStr + " values" + values + ";";
	}

	/**
	 * 動態塞值 PreparedStatement
	 * @param ps
	 * @param obj
	 */
	protected void setParameter(PreparedStatement ps, T obj) {
		/*
		返回一個Field對像數組，該數組反映由該Class對象表示的類或接口聲明的所有字段。
		這包括公共，受保護，默認（程序包）訪問和私有字段，但不包括繼承的字段。
		 */
		Field[] fields = obj.getClass().getDeclaredFields();
		IntStream.range(0, fields.length).forEach(i->{
			try {
				// PropertyDescriptor 描述 Java Bean 通過一對訪問器方法導出的一個屬性。
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[i].getName(), obj.getClass());

				//獲取應用於讀取屬性值的方法。
				Method method = propertyDescriptor.getReadMethod();

				/*
                invoke
                在具有指定參數的指定對像上，調用此Method對象表示的基礎方法。
                各個參數將自動解包以匹配原始形式參數，並且原始參數和引用參數都必鬚根據需要進行方法調用轉換。
                如果該方法正常完成，則將其返回的值返回給invoke的調用者；否則，返回false。
                 */
				Object value = method.invoke(obj);

				// 判斷該屬性是否有 ID Annotation
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

	/**
	 *
	 * @param rs
	 * @param cls
	 */
	protected Optional<T> resultSet2Bean(ResultSet rs, Class<T> cls)
			throws IOException, ServletException, IntrospectionException, InstantiationException,
			IllegalAccessException, InvocationTargetException, SQLException {

		// 動態生成 object
		T obj = cls.newInstance();

		/*
		返回一個Field對像數組，該數組反映由該Class對象表示的類或接口聲明的所有字段。
		這包括公共，受保護，默認（程序包）訪問和私有字段，但不包括繼承的字段。
		 */
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			String value = rs.getString(f.getName());

			// PropertyDescriptor 描述 Java Bean 通過一對訪問器方法導出的一個屬性。
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), cls);

			// 獲取應該用於寫入屬性值的方法。
			Method writeMethod = propertyDescriptor.getWriteMethod();

			if (propertyDescriptor.getPropertyType() == Character.class) {
				/*
                invoke
                在具有指定參數的指定對像上，調用此Method對象表示的基礎方法。
                各個參數將自動解包以匹配原始形式參數，並且原始參數和引用參數都必鬚根據需要進行方法調用轉換。
                如果該方法正常完成，則將其返回的值返回給invoke的調用者；否則，返回false。
                 */
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
