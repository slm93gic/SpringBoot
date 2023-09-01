package com.slm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
public class TestController {

    @RequestMapping("/t1")
    public String test1() {
        //classpath:/templates/test.html
        return "test";
    }

    @RequestMapping("/t2")
    public String test2(Model model) {
        model.addAttribute("msg", "Hello,Thymeleaf"); //存入数据
        //classpath:/templates/test.html
        return "test2";
    }


    @RequestMapping("/t3")
    public String test2(Map<String, Object> map) {
        //存入数据
        map.put("msg", "<h1>Hello</h1>");
        map.put("users", Arrays.asList("qinjiang", "kuangshen"));
        //classpath:/templates/test.html
        return "test3";
    }

}