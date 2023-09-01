package com.slm.spring.dao.impl;

import com.slm.spring.dao.UserDao;

public class UserDaoOracleImpl implements UserDao {

    @Override
    public void getUser() {
        System.out.println("Oracle获取用户数据");
    }
}
