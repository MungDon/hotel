package com.example.demo.dto.response.question;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResQuestionList {

    private Long question_sid;          // 문의 시퀀스

    private String title;               // 문의 제목

    private String content;             // 문의 내용
    
    private String answer_content;      // 답변 내용

    private String type_name;           // 문의 유형

    private String user_name;           // 문의 고객
    
    private String admin_name;          // 관리자명

    private String question_status;     // 문의 처리 상태

    private LocalDateTime created_date;  // 문의 생성일

    private LocalDateTime modified_date; // 문의 수정일

    private LocalDateTime answer_created_date;  // 답변 생성일

    private LocalDateTime answer_modified_date; // 답변 수정일

}
