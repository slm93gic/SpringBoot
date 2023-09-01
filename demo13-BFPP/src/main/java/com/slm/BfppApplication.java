package com.slm;

import com.slm.entity.ProcessorEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author limin shen
 * @description BeanFactoryPostProcessor的演示
 * @date 2023-09-01 15:05
 */
@SpringBootApplication
public class BfppApplication {

    @Resource
    private ProcessorEntity entity;

    public static void main(String[] args) {
        SpringApplication.run(BfppApplication.class, args);
        BfppApplication application = new BfppApplication();
        System.out.println(application.entity);
    }


}
