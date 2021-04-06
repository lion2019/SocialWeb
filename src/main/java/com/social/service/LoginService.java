package com.social.service;

import java.util.Optional;

import com.social.dao.UserDao;
import com.social.domain.User;

import net.sf.json.JSONObject;

public class LoginService {
	private UserDao userDao = new UserDao();
	
	public Optional<User> login(User user) {
		Optional<User> user2 = userDao.findByEmail(user.getEmail());
		return user2;
	}
}
