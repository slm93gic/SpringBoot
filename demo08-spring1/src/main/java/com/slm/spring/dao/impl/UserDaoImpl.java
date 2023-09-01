package com.slm.spring.dao.impl;

import com.slm.spring.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @Override
    public void getUser() {
        System.out.println("获取用户数据");
    }
}
