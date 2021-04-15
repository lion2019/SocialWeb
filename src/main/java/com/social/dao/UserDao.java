package com.social.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.social.domain.User;

public class UserDao extends BaseDao<User> {

	
	public Optional<User> findByEmail(String email) {
		String sql = "select * from user where email = ?";
		Optional<User> user = null;
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
