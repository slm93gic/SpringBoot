package com.slm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;


//数据校验
//注册bean到容器中
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {

    @Email(message = "邮箱格式错误")
    //name必须是邮箱格式
    private String name;

    private String env;

}

