package com.slm.spring;

import com.slm.spring.dao.impl.UserDaoMySqlImpl;
import com.slm.spring.dao.impl.UserDaoOracleImpl;
import com.slm.spring.service.UserService;
import com.slm.spring.service.impl.UserServiceImpl;

public class Test {

    public static void main(String[] args) {
//        UserService service = new UserServiceImpl();
//        service.getUser();
        
        UserServiceImpl service = new UserServiceImpl();
        service.setUserDao(new UserDaoMySqlImpl());
        service.getUser();
        //那我们现在又想用Oracle去实现呢
        service.setUserDao(new UserDaoOracleImpl());
        service.getUser();
    }

}
