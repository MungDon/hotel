package com.example.demo.dto.request.question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionAdd {

    private Long userSid;

    private String title;

    private String questionType;

    private String content;

    private String isSecret;

}
