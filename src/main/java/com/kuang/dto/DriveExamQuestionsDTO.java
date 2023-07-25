package com.kuang.dto;

import lombok.Data;

/**
 * @author ：yangan
 * @date ：2023/7/21 上午10:16
 * @description：
 * @version:
 */
@Data
public class DriveExamQuestionsDTO {
//    private Long id;
    private String questionId;
    private String mediaType;
//    private Short type;
//    private Short subject;
    private String chapterId;
    private String label;
    private String mediaId;
    private String mediaWidth;
    private String mediaHeight;
    private String answer;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
//    private String optionE;
//    private String optionF;
//    private String optionG;
//    private String optionH;
    private String difficulty;
    private String wrongRate;
    private String optionType;
    private String question;
    private String jieXi;
    private String conciseExplain;
    private String keywords;
    private String media;
    private String luJin;
    private String chapter;
    private String answers;
}