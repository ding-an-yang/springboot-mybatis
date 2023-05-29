package com.kuang.controller;

import com.kuang.dto.StudentDTO;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.service.StudentService;
import com.kuang.util.ExcelUtil;
import com.kuang.util.ExportPOIUtils;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Boolean addStudent(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.addStudent(studentDTO) > 0;
    }

    @PostMapping("/update")
    public Boolean updateStudent(@RequestBody @Valid StudentDTO studentDTO){return studentService.updateStudent(studentDTO) > 0;}

    @PostMapping("/delete/{studentId}")
    public Boolean deleteStudent(@PathVariable Integer studentId){return studentService.deleteStudent(studentId) > 0;}

    @PostMapping("/studentAanUserVo")
    public List<StudentAanUserVo> queryStudentAanUserList(){
        return studentService.queryStudentAanUserList();
    }

    @PostMapping("/selectUsers")
    public List<Users> selectUsers(){
        return studentService.selectUsers();
    }

    @PostMapping("/excelExport")
    public void callRecordExcelExport(HttpServletResponse res) {
        try {
            List<Student> students = studentService.queryStudentList();
            String fileName = "记录统计统计.xlsx";
            res.setContentType("application/vnd.ms-excel;charset=utf-8");
            res.setHeader("Content-Disposition" , "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8" ));
            List<Student> exportVoList = students.stream().map(privacyBill -> {
                Student exportVo = new Student();
                exportVo.setSid(privacyBill.getSid());
                exportVo.setName(privacyBill.getName());
                exportVo.setArddss(privacyBill.getArddss());
                exportVo.setAge(privacyBill.getAge());
                return exportVo;
            }).collect(Collectors.toList());
            String[] heads = {"学生ID","学生姓名","学生地址","学生年龄"};
            ExcelUtil.listToExcel(exportVoList, heads, fileName, res.getOutputStream());
        } catch (IOException e) {
            System.out.println("XXXX记录统计统计导出:{}"+e.getMessage());
        }
    }


    /**
     * 导出数据到excel表格
     * @param request
     * @param response
     */
    @PostMapping("/export2")
    @ResponseBody
    public String export(HttpServletRequest request, HttpServletResponse response){
        long start = System.currentTimeMillis();
        //根据手机号，查询数据库中需要导出的数据
        List<Student> students = studentService.queryStudentList();

        //创建excel表头
        List<String> column = new ArrayList<>();
        column.add("序号");
        column.add("姓名");
        column.add("年龄");
        column.add("地址");
        column.add("价格");
        column.add("测试标题1");
        column.add("测试标题2");
        column.add("测试标题3");
        column.add("测试标题4");
        column.add("测试标题5");
        //表头对应的数据
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> dataMap = new HashMap<>();
        int count = 0;
        //遍历获取到的需要导出的数据，k要和表头一样
        for (Student student : students) {

            dataMap.put("序号",student.getSid());
            dataMap.put("姓名",student.getName());
            dataMap.put("年龄",student.getAge());
            dataMap.put("地址",student.getArddss());
            dataMap.put("价格",student.getPrice());
            dataMap.put("测试标题1",student.getTitle());
            dataMap.put("测试标题2",student.getTitle2());
            dataMap.put("测试标题3",student.getTitle3());
            dataMap.put("测试标题4",student.getTitle4());
            dataMap.put("测试标题5",student.getTitle5());
            data.add(dataMap);
            if (data.size() > 2000){
                break;
            }
        }

        //调用导出工具类
        ExcelUtil.exportExcel("工作人员表",column,data,request,response);
        long end = System.currentTimeMillis();
        System.out.println(2000 + "条数据需要" + (end - start));
        return "导出完成";
    }

//    @ResponseBody
    @GetMapping("/export")
    public String exportExcel(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        long start = System.currentTimeMillis();
        String fileName = "创建的User";
        List<Student> students = studentService.queryStudentList().stream().limit(2000).collect(Collectors.toList());

        // 列名
        String columnNames[] = {"序号", "姓名", "年龄","地址","价格","测试标题1","测试标题2","测试标题3","测试标题4","测试标题5"};
        // map中的key,即实体类中的字段名
        String keys[] = {"sid", "age", "name","arddss","price","title","title2","title3","title4","title5"};
        ExportPOIUtils.start_download(response, request, fileName, students, columnNames, keys);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "OK";
    }


    @GetMapping("/add2")
    public void addStudent2(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAge(234433333);
        studentDTO.setName("jack");
        studentDTO.setArddss("dddd");
        studentDTO.setPrice(new BigDecimal("123456789012345.55555"));
        studentDTO.setTitle("计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈");
        studentDTO.setTitle2("计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈");
        studentDTO.setTitle3("计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈");
        studentDTO.setTitle4("计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈");
        studentDTO.setTitle5("计算机积分讲讲价讲讲价讲讲价斤斤计ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较ajffff计算机积分讲讲价讲讲价讲讲价斤斤计较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈军哈哈哈哈军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈较军军斤斤计较军斤斤计较军斤斤计较军军斤斤计较军斤斤计较斤斤计较军哈哈哈哈");
        for (int i = 0; i < 50000; i++) {
            studentDTO.setSid(i);
            studentService.addStudent(studentDTO);
        }
    }

}
