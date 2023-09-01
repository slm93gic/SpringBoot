package com.slm.service;

import com.slm.anno.MyServiceAnno;

/**
 * @author limin shen
 * @date 2023-09-01 15:08
 */
@MyServiceAnno
public class TestAnnoService {

    private String userName = "张老三";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
