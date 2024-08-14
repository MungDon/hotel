package com.example.demo.dto.response.question;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResQuestionList {

    private Long question_sid;

    private String title;

    private String content;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;
}
