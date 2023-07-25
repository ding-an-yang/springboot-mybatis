package com.kuang.service;

import com.kuang.dto.StudentDTO;
import com.kuang.model.DriveExamQuestions;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ：xxx
 * @date ：2022/8/3 下午10:46
 * @description：业务接口层
 * @version:
 */
public interface DriveExamQuestionsService {
    /**
     * 查询所有学生列表信息
     * @return 所有信息
     */
    List<DriveExamQuestions> queryDriveExamQuestionsList();

    /**
     * 添加学生信息* @return 成功返回 1 失败返回 0
     */
    void addDriveExamQuestions(MultipartFile file);

    /**
     * 修改学生信息
     * @return 成功返回 1 失败返回 0
     */
    Integer updateDriveExamQuestions(DriveExamQuestions driveExamQuestions);

}