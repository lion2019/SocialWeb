package com.social.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.social.domain.User;

public class UserDao extends BaseDao<User> {

	/**
	 * 以帳號(email)查 user資料
	 */
	public Optional<User> findByEmail(String email) {
		String sql = "select * from user where email = ?";
		Optional<User> user = Optional.empty();
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, email);
			try(ResultSet rs = ps.executeQuery();){
				while(rs.next()) {
					user = resultSet2Bean(rs, User.class);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
