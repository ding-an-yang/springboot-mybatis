package com.kuang.controller;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
//    修改了一些东西
    private UserMapper userMapper;
    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        return userList;
    }
    @GetMapping("/queryUserById")
    private User queryUserById(){
        User user = userMapper.queryUserById(4);
        return user;
    }
    @GetMapping("/addUser")
    private String addUser(){
        int i = userMapper.addUser(new User(13, "的发射点", "88888", "77888@qq.com"));
        if (i>0){
            System.out.println("插入一个新成员成功");
        }
        return "ok";
    }
    @GetMapping("/UpdateUser")
    private String UpdateUser(){
        int i = userMapper.UpdateUser(new User(6, "啥啥啥", "1234", "8888@qq.com"));
        if (i>0){
            System.out.println("修改一个新成员成功");
        }
        return "ok";
    }
    @GetMapping("/deleteUser")
    private String  deleteUser(){
        int i = userMapper.deleteUser(1);
        if (i>0){
            System.out.println("删除成功");
        }
        return "ok";
    }
}
