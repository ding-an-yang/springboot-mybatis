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

    @Update("insert into Drive_Exam_Questions(question_id,media_type,chapter_id,label,media_id,media_width," +
            "media_height,answer,option_a,option_b,option_c,option_d,difficulty,wrong_rate,option_type," +
            "question,explain,concise_explain,keywords,media,m_url,chapter,answers)" +
            " VALUES(#{questionId},#{mediaType},#{chapterId},#{label},#{mediaId},#{mediaWidth},#{mediaHeight}," +
            "#{answer},#{optionA},#{optionB},#{optionC},#{optionD},#{difficulty},#{wrongRate},#{optionType}" +
            ",#{question},#{cExplain},#{conciseExplain},#{keywords},#{media},#{mUrl},#{chapter},#{answers})")
    Integer insertDrive(String questionId, String mediaType, String chapterId,String label, String mediaId,
                          String mediaWidth, String mediaHeight, String answer,String optionA,String optionB,String optionC,
                          String optionD, String difficulty, String wrongRate, String optionType, String question, String cExplain,
                          String conciseExplain, String keywords, String media, String mUrl, String chapter, String answers);


}
