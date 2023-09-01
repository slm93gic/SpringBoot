package com.slm.spring.service.impl;

import com.slm.spring.dao.UserDao;
import com.slm.spring.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}