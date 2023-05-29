package com.kuang.service;

import com.kuang.dto.StudentDTO;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.vo.StudentAanUserVo;

import java.util.List;

/**
 * @author ：xxx
 * @date ：2022/8/3 下午10:46
 * @description：业务接口层
 * @version:
 */
public interface StudentService {
    /**
     * 查询所有学生列表信息
     * @return 所有信息
     */
    List<Student> queryStudentList();

    /**
     * 根据id查询单个学生信息
     * @param studentId 传入
     * @return 返回单个学生信息
     */
    Student queryStudentById(Integer studentId);

    /**
     * 添加学生信息
     * @param studentDTO 添加参数
     * @return 成功返回 1 失败返回 0
     */
    Integer addStudent(StudentDTO studentDTO);

    /**
     * 修改学生信息
     * @param studentDTO 修改参数
     * @return 成功返回 1 失败返回 0
     */
    Integer updateStudent(StudentDTO studentDTO);

    /**
     * 根据id删除一个用户
     * @param studentId 删除id
     * @return 成功返回 1 失败返回 0
     */
    Integer deleteStudent(Integer studentId);

    /**
     * 两表联查
     * @return List<StudentAanUserVo>
     */
    List<StudentAanUserVo> queryStudentAanUserList();

    List<Users> selectUsers();
}