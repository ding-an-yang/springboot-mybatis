package com.kuang.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：yangan
 * @date ：2022/10/31 下午2:46
 * @description：
 * @version:
 */
@Data
public class StudentAanUserVo {
    // Student
    private  Integer sid;
    private  Integer age;
    private  String name;
    private  String arddss;

    // Users
    private  Integer uid;
    private  String uname;
    private  String password;
    private  String email;
}