package com.kuang.model;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;

/**
 * @author ：yangan
 * @date ：2023/7/21 上午10:16
 * @description：
 * @version:
 */
@Data
public class DriveExamQuestions {
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private String id;
    private String questionId;
    private String mediaType;
    private String type;
    private String subject;
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
    private String optionE;
    private String optionF;
    private String optionG;
    private String optionH;
    private String difficulty;
    private String wrongRate;
    private String optionType;
    private String question;
    private String jieXi;
    private String conciseExplain;
    private String keywords;
    private String media;
    private String mId;
    private String mMediaContent;
    private String luJin;
    private String chapter;
    private String answers;
}