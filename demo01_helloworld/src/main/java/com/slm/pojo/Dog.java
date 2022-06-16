package com.slm.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Dog {

    @Value("阿黄")
    private String name;

    @Value("18")
    private Integer age;

}
