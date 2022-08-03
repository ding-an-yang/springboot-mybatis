package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.IntrospectionException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UStudent {
    private  Integer sid;
    private  Integer sage;
    private  String sname;
    private  String sarddss;
    private  Integer uid;
    private  String uname;
    private  String upassword;
    private  String uemail;

}
