package com.slm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class FirstController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //查询employee表中所有数据
    //List 中的1个 Map 对应数据库的 1行数据
    //Map 中的 key 对应数据库的字段名，value 对应数据库的字段值
    @GetMapping("/list")
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //新增一个用户
    @GetMapping("/add")
    public String addUser() {
        //插入语句，注意时间问题
        String sql = "INSERT INTO user(id, name, pwd, last_name) VALUES (11, '狂神', '123456', 'wocao')";
        jdbcTemplate.update(sql);
        return "addOk";
    }

    //修改用户信息
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id) {
        //插入语句
        String sql = "update user set last_name=?  where id=" + id;
        //数据
        Object[] objects = new Object[1];
        objects[0] = "秦疆";
        jdbcTemplate.update(sql, objects);
        //查询
        return "updateOk";
    }

    //删除用户
    @GetMapping("/delete/{id}")
    public String delUser(@PathVariable("id") int id) {
        //插入语句
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql, id);
        //查询
        return "deleteOk";
    }


}
