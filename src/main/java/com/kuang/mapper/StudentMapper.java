package com.kuang.mapper;

import com.kuang.pojo.Student;
import com.kuang.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository//说明被Spring整合了
public interface StudentMapper extends tk.mybatis.mapper.common.Mapper<Student> {

    @Select("SELECT * from student")
    List<Student> findStudent();

    @Select("SELECT age,name from student")
    List<Student> findStudent2();

    @Select("select S.* from student s JOIN users u on s.sid=u.id")
    List<Student> findStudent3();

}
