package com.kuang.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Users {
    private  Integer id;
    private  String name;
    private  String password;
    private  String email;
    private List<Student> studentsList;
    private Student student;

}
