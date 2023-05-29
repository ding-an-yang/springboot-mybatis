package com.kuang.service.serviceImpl;

import com.kuang.dto.StudentDTO;
import com.kuang.mapper.StudentMapper;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.service.StudentService;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：yangan
 * @date ：2022/8/3 下午9:27
 * @description：
 * @version:
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public List<Student> queryStudentList() {
        return studentMapper.queryStudentList();
    }

    @Override
    public Student queryStudentById(Integer studentId) {
        return studentMapper.queryStudentById(studentId);
    }

    @Override
    public Integer addStudent(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        return studentMapper.insertSelective(student);
    }

    @Override
    public Integer updateStudent(StudentDTO studentDTO) {
        return studentMapper.updateStudent(studentDTO.getAge(), studentDTO.getName(),
                studentDTO.getArddss(), studentDTO.getSid());
    }

    @Override
    public Integer deleteStudent(Integer studentId) {
        return studentMapper.deleteStudent(studentId);
    }

    @Override
    public List<StudentAanUserVo> queryStudentAanUserList() {
        return studentMapper.queryAllList();
    }

    @Override
    public List<Users> selectUsers(){
        return studentMapper.selectUsers();
    }

}