package com.kuang.controller;

import com.kuang.dto.StudentDTO;
import com.kuang.model.Student;
import com.kuang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/list")
    public List<Student> queryUserList(){
        return studentService.queryStudentList();
    }

    @PostMapping("/query_by_id/{studentId}")
    public Student queryStudentById(@PathVariable Integer studentId){return studentService.queryStudentById(studentId);}

    @PostMapping("/add")
    public Boolean addStudent(@RequestBody @Valid StudentDTO studentDTO){return studentService.addStudent(studentDTO) > 0;}

    @PostMapping("/update")
    public Boolean updateStudent(@RequestBody @Valid StudentDTO studentDTO){return studentService.updateStudent(studentDTO) > 0;}

    @PostMapping("/delete/{studentId}")
    public Boolean deleteStudent(@PathVariable Integer studentId){return studentService.deleteStudent(studentId) > 0;}
}
