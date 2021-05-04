package com.social.service;

import java.sql.SQLException;

import com.social.dao.UserDao;
import com.social.domain.User;
import com.social.exception.BaseException;
import com.social.exception.ResponseEnum;
import com.social.exception.UserException;

public class RegisterService {

    private UserDao userDao = new UserDao();

    public boolean addUser(User user) throws UserException {
        try {
            return userDao.insert(user);
        } catch (SQLException e) {
            throw new UserException(e.getErrorCode(), e.getMessage());
        }
//		return true;
    }
}
