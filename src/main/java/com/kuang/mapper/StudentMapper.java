package com.kuang.mapper;

import com.kuang.dto.StudentDTO;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.vo.StudentAanUserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper extends tk.mybatis.mapper.common.Mapper<Student> {

    /**
     * 查询所有学生列表信息
     * @return 所有信息
     */
    @Select("SELECT * from student")
    List<Student> queryStudentList();

    /**
     * 根据id查询单个学生信息
     * @param studentId 传入
     * @return 返回单个学生信息
     */
    @Select("SELECT * from student where sid = #{studentId}")
    Student queryStudentById(Integer studentId);

    /**
     * 修改学生信息
     * @param age age
     * @param name name
     * @param arddss arddss
     * @param sid sid
     * @return 成功返回 1 失败返回 0
     */
    @Update("update student set age = #{age}, name = #{name}, arddss = #{arddss} where sid=#{sid}")
    Integer updateStudent(Integer age, String name, String arddss,Integer sid);

    /**
     * 根据id删除一个用户
     * @param studentId 删除id
     * @return 成功返回 1 失败返回 0
     */
    @Delete("delete from student where sid = #{studentId}")
    Integer deleteStudent(Integer studentId);

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
            "   </where>" +
            "</script>")
    List<Student> findCount2(List<String> ssname, List<String> uuname);

    @Select("SELECT s.sid,s.name,s.age,s.arddss,u.id uid,u.name uname,u.password,u.email from student s,users u")
    List<StudentAanUserVo> queryAllList();

    @Select("select * from  users")
    List<Users> selectUsers();
}
