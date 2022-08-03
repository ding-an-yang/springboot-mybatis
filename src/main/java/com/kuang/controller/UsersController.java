package com.kuang.controller;

import com.kuang.dto.UsersDTO;
import com.kuang.model.*;
import com.kuang.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/list")
    public List<Users> queryUserList(){
        return usersService.queryUserList();
    }

    @PostMapping("/query_by_id/{userId}")
    private Users queryUserById(@PathVariable Integer userId){
        return usersService.queryUserById(userId);
    }

    @PostMapping("/add")
    public Boolean addUser(@RequestBody @Valid UsersDTO usersDTO){
        return usersService.addUser(usersDTO) > 0;
    }

    @PostMapping("/update")
    public Boolean updateUser(@RequestBody @Valid UsersDTO usersDTO){
        return usersService.updateUser(usersDTO) > 0;
    }

    @PostMapping("/delete/{userId}")
    public Boolean deleteUser(@PathVariable Integer userId){
        return usersService.deleteUser(userId) > 0;
    }
}
