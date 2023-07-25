package com.kuang.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.dto.DriveExamQuestionsDTO;
import com.kuang.dto.StudentDTO;
import com.kuang.mapper.DriveExamQuestionsMapper;
import com.kuang.mapper.StudentMapper;
import com.kuang.model.DriveExamQuestions;
import com.kuang.model.Student;
import com.kuang.model.Users;
import com.kuang.service.DriveExamQuestionsService;
import com.kuang.service.StudentService;
import com.kuang.util.ExcelUtils;
import com.kuang.vo.StudentAanUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author ：yangan
 * @date ：2022/8/3 下午9:27
 * @description：
 * @version:
 */
@Service
public class DriveExamQuestionsServiceImpl implements DriveExamQuestionsService {

    @Autowired
    DriveExamQuestionsMapper driveExamQuestionsMapper;
    @Autowired
    ObjectMapper objectMapper;


    @Override
    public List<DriveExamQuestions> queryDriveExamQuestionsList() {
        return null;
    }

    @Override
    public void addDriveExamQuestions(MultipartFile file) {
//        String fileName = "our-20230720.json";
//        ClassLoader  classLoader =  DriveExamQuestionsServiceImpl.class.getClassLoader();
//        BufferedReader reader = null;
//        InputStream inputStream = classLoader.getResourceAsStream(fileName);
//        reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder content = new StringBuilder();
//        String line = null;
//        try {
//            line = reader.readLine();
//            while (line != null) {//line != null
//                DriveExamQuestions driveExamQuestions2 = new DriveExamQuestions();
//                if (line.contains("QuestionId:")){
//                    driveExamQuestions2.setQuestionId(line.substring(11));
//                }
//                if (line.contains("MediaType:")){
//                    driveExamQuestions2.setMediaType(line.substring(10));
//                }
//                if (line.contains("ChapterId:")){
//                    driveExamQuestions2.setChapterId(line.substring(10));
//                }
//                if (line.contains("Label:")){
//                    driveExamQuestions2.setLabel(line.substring(6));
//                }
//                if (line.contains("MediaId:")){
//                    driveExamQuestions2.setMediaId(line.substring(8));
//                }
//                if (line.contains("MediaWidth:")){
//                    driveExamQuestions2.setMediaWidth(line.substring(11));
//                }
//                if (line.contains("MediaHeight:")){
//                    driveExamQuestions2.setMediaHeight(line.substring(12));
//                }
//                if (line.contains("Answer:")){
//                    driveExamQuestions2.setAnswer(line.substring(7));
//                }
//                if (line.contains("OptionA:")){
//                    driveExamQuestions2.setOptionA(line.substring(8));
//                }
//                if (line.contains("OptionB:")){
//                    driveExamQuestions2.setOptionB(line.substring(8));
//                }
//                if (line.contains("OptionC:")){
//                    driveExamQuestions2.setOptionC(line.substring(8));
//                }
//                if (line.contains("OptionD:")){
//                    driveExamQuestions2.setOptionD(line.substring(8));
//                }
//                if (line.contains("Difficulty:")){
//                    driveExamQuestions2.setDifficulty(line.substring(11));
//                }
//                if (line.contains("WrongRate:")){
//                    driveExamQuestions2.setWrongRate(line.substring(10));
//                }
//                if (line.contains("OptionType:")){
//                    driveExamQuestions2.setOptionType(line.substring(11));
//                }
//                if (line.contains("Question:")){
//                    driveExamQuestions2.setQuestion(line.substring(9));
//                }
//                if (line.contains("Explain:")){
//                    driveExamQuestions2.setExplain(line.substring(8));
//                }
//                if (line.contains("ConciseExplain:")){
//                    driveExamQuestions2.setConciseExplain(line.substring(15));
//                }
//                if (line.contains("Keywords:")){
//                    driveExamQuestions2.setKeywords(line.substring(9));
//                }
//                if (line.contains("Media:")){
//                    driveExamQuestions2.setMedia(line.substring(6));
//                }
//                if (line.contains("MediaContent:")){
//                    driveExamQuestions2.setMMediaContent(line.substring(13));
//                }
//                if (line.contains("Url:")){
//                    driveExamQuestions2.setMUrl(line.substring(4));
//                }
//                if (line.contains("Chapter:")){
//                    driveExamQuestions2.setChapter(line.substring(8));
//                }
//                if (line.contains("Answers:")){
//                    driveExamQuestions2.setAnswers(line.substring(8));
//                }
//                content.append(line);
//                line = reader.readLine();
//
//                driveExamQuestionsMapper.insertSelective(driveExamQuestions2);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
            // 判断用户角色 // TODO

            String fileName = file.getOriginalFilename();
            if (Objects.isNull(fileName)) {
                System.out.println("文件不存在。");
            }
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            if (!suffixName.equals(".xlsx")) {
                System.out.println("不是Excel文件。");
            }
            InputStream is = null;
            List<Map<String, Object>> maps = null;
            try {
                is = file.getInputStream();
                // 读取Excel中的数据
                maps = ExcelUtils.readeExcelData(is, 0, 0, 1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("文件读取失败！");
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            List<Map<String, Object>> mapList = new ArrayList<>();
        assert maps != null;
        maps.forEach(map -> {
                // 设置Excel标题与数据库字段匹配
                Map<String, Object> headMap = new HashMap<>();
                headMap.put("questionId", map.get("QuestionId")); // 类目编号
                headMap.put("mediaType", map.get("MediaType")); // 类目名称
                headMap.put("chapterId", map.get("ChapterId")); // 产品名称
                headMap.put("label", map.get("Label")); // 产品编号
                headMap.put("mediaId", map.get("MediaId")); // 产品型号
                headMap.put("mediaWidth", map.get("MediaWidth")); // 设置是否隐藏
                headMap.put("mediaHeight", map.get("MediaHeight")); // 计量单位
                headMap.put("answer", map.get("Answer")); // 产品规格
                headMap.put("optionA", map.get("OptionA")); // 商标
                headMap.put("optionB", map.get("OptionB")); // 重量 1->g
                headMap.put("optionC", map.get("OptionC")); // 税率
                headMap.put("optionD", map.get("OptionD")); // 序号
                headMap.put("difficulty", map.get("Difficulty")); // 产地
                headMap.put("wrongRate", map.get("WrongRate")); // 产品描述
                headMap.put("optionType", map.get("OptionType")); // 图片路径
                headMap.put("question", map.get("Question")); // 图片路径
                headMap.put("jieXi", map.get("Explain")); // 图片路径
                headMap.put("conciseExplain", map.get("ConciseExplain"));
                headMap.put("keywords", map.get("Keywords")); // 图片路径
                headMap.put("media", map.get("Media")); // 图片路径
                headMap.put("luJin", map.get("Url")); // 图片路径
                headMap.put("chapter", map.get("Chapter")); // 图片路径
                headMap.put("answers", map.get("Answers")); // 图片路径
                mapList.add(headMap);
            });
            mapList.forEach(map -> {
                DriveExamQuestionsDTO driveExamQuestionsDTO = objectMapper.convertValue(map, DriveExamQuestionsDTO.class);
                DriveExamQuestions driveExamQuestions = new DriveExamQuestions();
                BeanUtils.copyProperties(driveExamQuestionsDTO, driveExamQuestions);
                driveExamQuestionsMapper.insertSelective(driveExamQuestions);

//                driveExamQuestionsMapper.insertDrive(driveExamQuestionsDTO.getQuestionId(),driveExamQuestionsDTO.getMediaType(),
//                        driveExamQuestionsDTO.getChapterId(),driveExamQuestionsDTO.getLabel(),driveExamQuestionsDTO.getMediaId(),
//                        driveExamQuestionsDTO.getMediaWidth(),driveExamQuestionsDTO.getMediaHeight(),driveExamQuestionsDTO.getAnswer(),
//                        driveExamQuestionsDTO.getOptionA(),driveExamQuestionsDTO.getOptionB(),driveExamQuestionsDTO.getOptionC(),
//                        driveExamQuestionsDTO.getOptionD(),driveExamQuestionsDTO.getDifficulty(),driveExamQuestionsDTO.getWrongRate(),
//                        driveExamQuestionsDTO.getOptionType(),driveExamQuestionsDTO.getQuestion(),driveExamQuestionsDTO.getCExplain(),
//                        driveExamQuestionsDTO.getConciseExplain(),driveExamQuestionsDTO.getKeywords(),driveExamQuestionsDTO.getMedia(),
//                        driveExamQuestionsDTO.getMUrl(),driveExamQuestionsDTO.getChapter(),driveExamQuestionsDTO.getAnswers());
            });
    }

    @Override
    public Integer updateDriveExamQuestions(DriveExamQuestions driveExamQuestions) {
        return null;
    }
}