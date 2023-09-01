package com.kuang.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
public class User {

    private int id;//id
    private String name;//姓名
    private String pwd;//密码

}
