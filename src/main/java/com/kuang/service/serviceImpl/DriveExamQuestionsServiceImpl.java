package com.kuang.service.serviceImpl;

import com.kuang.dto.StudentDTO;
import com.kuang.mapper.DriveExamQuestionsMapper;
import com.kuang.mapper.StudentMapper;
import com.kuang.model.DriveExamQuestions;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.service.DriveExamQuestionsService;
import com.kuang.service.StudentService;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author ：yangan
 * @date ：2022/8/3 下午9:27
 * @description：
 * @version:
 */
@Service
public class DriveExamQuestionsServiceImpl implements DriveExamQuestionsService {

    @Resource
    DriveExamQuestionsMapper driveExamQuestionsMapper;


    @Override
    public List<DriveExamQuestions> queryDriveExamQuestionsList() {
        return null;
    }

    @Override
    public Integer addDriveExamQuestions(DriveExamQuestions driveExamQuestions) {
        String fileName = "our-20230720.json";
        ClassLoader  classLoader =  DriveExamQuestionsServiceImpl.class.getClassLoader();
        BufferedReader reader = null;
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();
        String line = null;
        try {
            line = reader.readLine();
            while (line != null) {//line != null
                DriveExamQuestions driveExamQuestions2 = new DriveExamQuestions();
                if (line.contains("QuestionId:")){
                    driveExamQuestions2.setQuestionId(line.substring(11));
                }
                if (line.contains("MediaType:")){
                    driveExamQuestions2.setMediaType(line.substring(10));
                }
                if (line.contains("ChapterId:")){
                    driveExamQuestions2.setChapterId(line.substring(10));
                }
                if (line.contains("Label:")){
                    driveExamQuestions2.setLabel(line.substring(6));
                }
                if (line.contains("MediaId:")){
                    driveExamQuestions2.setMediaId(line.substring(8));
                }
                if (line.contains("MediaWidth:")){
                    driveExamQuestions2.setMediaWidth(line.substring(11));
                }
                if (line.contains("MediaHeight:")){
                    driveExamQuestions2.setMediaHeight(line.substring(12));
                }
                if (line.contains("Answer:")){
                    driveExamQuestions2.setAnswer(line.substring(7));
                }
                if (line.contains("OptionA:")){
                    driveExamQuestions2.setOptionA(line.substring(8));
                }
                if (line.contains("OptionB:")){
                    driveExamQuestions2.setOptionB(line.substring(8));
                }
                if (line.contains("OptionC:")){
                    driveExamQuestions2.setOptionC(line.substring(8));
                }
                if (line.contains("OptionD:")){
                    driveExamQuestions2.setOptionD(line.substring(8));
                }
                if (line.contains("Difficulty:")){
                    driveExamQuestions2.setDifficulty(line.substring(11));
                }
                if (line.contains("WrongRate:")){
                    driveExamQuestions2.setWrongRate(line.substring(10));
                }
                if (line.contains("OptionType:")){
                    driveExamQuestions2.setOptionType(line.substring(11));
                }
                if (line.contains("Question:")){
                    driveExamQuestions2.setQuestion(line.substring(9));
                }
                if (line.contains("Explain:")){
                    driveExamQuestions2.setExplain(line.substring(8));
                }
                if (line.contains("ConciseExplain:")){
                    driveExamQuestions2.setConciseExplain(line.substring(15));
                }
                if (line.contains("Keywords:")){
                    driveExamQuestions2.setKeywords(line.substring(9));
                }
                if (line.contains("Media:")){
                    driveExamQuestions2.setMedia(line.substring(6));
                }
                if (line.contains("MediaContent:")){
                    driveExamQuestions2.setMMediaContent(line.substring(13));
                }
                if (line.contains("Url:")){
                    driveExamQuestions2.setMUrl(line.substring(4));
                }
                if (line.contains("Chapter:")){
                    driveExamQuestions2.setChapter(line.substring(8));
                }
                if (line.contains("Answers:")){
                    driveExamQuestions2.setAnswers(line.substring(8));
                }
                content.append(line);
                line = reader.readLine();

                driveExamQuestionsMapper.insertSelective(driveExamQuestions2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Integer updateDriveExamQuestions(DriveExamQuestions driveExamQuestions) {
        return null;
    }
}