package com.kuang.mapper;

import com.kuang.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends tk.mybatis.mapper.common.Mapper<Student> {

    @Select("SELECT * from student")
    List<Student> findStudent();

    @Select("SELECT age,name from student")
    List<Student> findStudent2();

    @Select("select S.* from student s JOIN users u on s.sid=u.id")
    List<Student> findStudent3();

    @Select("<script>" +
            "   select u.id uid,u.name uname,u.password upassword,u.email uemail,s.sid,s.age sage,s.name sname,s.arddss sarddss from users u join student s on u.id=s.sid" +
            "   <where>" +
            "       <if test = 'ssname != null and ssname.size() != 0'>" +
            "           and s.name in" +
            "           <foreach item='name' collection='ssname' open='(' separator=',' close=')'>" +
            "                #{name}" +
            "           </foreach>" +
            "      </if>" +
            "       <if test = 'uuname != null and uuname.size() != 0'>" +
            "           and u.name in" +
            "           <foreach item='name' collection='uuname' open='(' separator=',' close=')'>" +
            "                #{name}" +
            "           </foreach>" +
            "      </if>" +
            "      and 1=1" +
            "   </where>" +
            "</script>")
    List<Student> findCount2(List<String> ssname, List<String> uuname);
}
