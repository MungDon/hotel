package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionStatus {

    ANSWER_COMPLETE("답변완료"),
    ANSWER_WAITING("답변대기");

    private final String status;

}
