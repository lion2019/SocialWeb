package com.social.service;

import java.sql.SQLException;

import com.social.dao.UserDao;
import com.social.domain.User;

public class RegisterService {

	private UserDao userDao = new UserDao();
	
	public boolean addUser(User user) {
		return userDao.insert(user);
//		return true;
	}
}
