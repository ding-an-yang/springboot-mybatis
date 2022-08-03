package com.kuang.pojo;

import lombok.Data;

import java.util.List;

@Data
public class StudentAndUsers {
    private List<Users> userList;
    private  List<Student> studentList;
}
