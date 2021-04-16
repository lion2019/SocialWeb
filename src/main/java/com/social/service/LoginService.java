package com.social.service;

import com.social.dao.UserDao;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;

public class LoginService {
	private UserDao userDao = new UserDao();
	
	/**
	 * @param user
	 * @return 
	 * @throws BaseException user.email not found or password error 
	 */
	public User login(User user) throws BaseException {
		User userInfo = userDao.findByEmail(user.getEmail())
				.orElseThrow(()-> new BaseException(ResponseEnum.user_not_found));
		
		if(checkPassword(user, userInfo)) {
			return userInfo;
		}else {
			throw new BaseException(ResponseEnum.password_error);
		}
	}
	
	
	public boolean checkPassword(User userRequest, User userInfo) {
		return userRequest.getPassword().equals(userInfo.getPassword());
	}
}
