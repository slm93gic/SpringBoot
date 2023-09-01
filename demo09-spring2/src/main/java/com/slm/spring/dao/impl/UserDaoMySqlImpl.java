package com.slm.spring.dao.impl;

import com.slm.spring.dao.UserDao;

public class UserDaoMySqlImpl implements UserDao {

    @Override
    public void getUser() {
        System.out.println("MySql获取用户数据");
    }
}