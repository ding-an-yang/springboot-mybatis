package com.kuang.mapper;

import com.kuang.model.DriveExamQuestions;
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
public interface DriveExamQuestionsMapper extends tk.mybatis.mapper.common.Mapper<DriveExamQuestions> {

    @Select("SELECT * from Drive_Exam_Questions")
    List<DriveExamQuestions> queryStudentList();

    @Update("update Drive_Exam_Questions set age = #{age}, name = #{name}, arddss = #{arddss} where sid=#{sid}")
    Integer updateStudent(Integer age, String name, String arddss,Integer sid);

}
