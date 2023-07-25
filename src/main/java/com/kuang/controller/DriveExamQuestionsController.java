package com.kuang.controller;

import com.kuang.dto.StudentDTO;
import com.kuang.model.DriveExamQuestions;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.service.DriveExamQuestionsService;
import com.kuang.service.StudentService;
import com.kuang.util.ExcelUtil;
import com.kuang.util.ExportPOIUtils;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/drive")
public class DriveExamQuestionsController {

    @Autowired
    DriveExamQuestionsService driveExamQuestionsService;


    @PostMapping("/add")
    public void addList(@RequestParam("file") MultipartFile file) {
        driveExamQuestionsService.addDriveExamQuestions(file);
    }

}
