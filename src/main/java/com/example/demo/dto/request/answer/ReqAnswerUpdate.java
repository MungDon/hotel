package com.example.demo.dto.request.answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqAnswerUpdate {

    private Long questionSid;   // 문의 시퀀스   

    private String content;     // 답변 내용
}
