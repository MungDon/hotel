package com.example.demo.dto.response.questiontype;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResQuestionTypeList {

    private Long question_type_sid;

    private String type_name;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;
}
