package com.kuang.controller;

import com.kuang.mapper.StudentMapper;
import com.kuang.mapper.UsersMapper;
import com.kuang.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UsersController {
    @Autowired
//    修改了一些东西
    private UsersMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @GetMapping("/queryUserList")
    public List<Users> queryUserList(){
        List<Users> userList = userMapper.queryUserList();
        return userList;
    }
    @GetMapping("/queryUserById")
    private Users queryUserById(){
        Users user = userMapper.queryUserById(4);
        return user;
    }

    @GetMapping("/deleteUser")
    private String  deleteUser(){
        int i = userMapper.deleteUser(1);
        if (i>0){
            System.out.println("删除成功");
        }
        return "ok";
    }

    @GetMapping("/find")
    private List<UStudent> findUsersAndStudent(){
        return userMapper.findUsersAndStudent();
    }

    @GetMapping("/find2")
    private List<Object> findUsers(){
        List<Student> student = studentMapper.findStudent();
        List<Users> users = userMapper.queryUserList();
        List<Object> collect1 = Stream.of(student, users).flatMap(Collection::stream).collect(Collectors.toList());
        return collect1;
    }

    @GetMapping("/find3")
    private List<UStudent2> findUsersAndStudentCount(){
        UStudent uStudent = new UStudent();
        uStudent.setSname("jack");
        uStudent.setUname("k");
        List<UStudent2> count = userMapper.findCount(uStudent.getSname(), uStudent.getUname());
        System.out.println(uStudent.getSname()+uStudent.getUname());
        for (UStudent2 c:count) {
            System.out.println(c);
        }
        return count;
    }

    @GetMapping("/find4")
    private List<UStudent> findCount2(){
        List<String> list1 = new ArrayList<>();
        list1.add("jack1234");
        list1.add("12jack34");
        list1.add("1234jack");
        list1.add("1jack234");
        List<String> list2 = new ArrayList<>();
        list2.add("jack");
        list2.add("jack");
        list2.add("jack");
        list2.add("jack");
        return userMapper.findCount2(list1,list2);
    }

    @GetMapping("/stulist")
    public List<Student> selectStudent(){
        return studentMapper.findStudent2();
    }


    @GetMapping("/selectAll")
    public List<Users> selectAll(){
        List<Users> users = userMapper.queryUserList();
        List<Student> student = studentMapper.findStudent3();
        StudentAndUsers studentAndUsers = new StudentAndUsers();
        studentAndUsers.setUserList(users);
        studentAndUsers.setStudentList(student);
        //使用stream流把list1和list2根据属性ticketId合并一个list集合
        List<Users> list = users.stream().map(m -> {
            student.stream().filter(m2-> Objects.equals(m.getId(),m2.getSid())).forEach(m2-> {
                m.setId(m2.getSid());
                m2.getArddss();
                m2.getAge();
            });
            return m;
        }).collect(Collectors.toList());
        return list;
    }

    @GetMapping("/addStudent")
    private String addUser2(){
        Student student = new Student();
        student.setSid(123);
        student.setName("javkkk");
        student.setAge(34);
        student.setArddss("dgtseffs");
        int i = studentMapper.insertSelective(student);
        if (i>0){
            System.out.println("插入一个新成员成功");
        }
        return "ok";
    }
}
