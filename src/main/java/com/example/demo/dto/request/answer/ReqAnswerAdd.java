package com.example.demo.dto.request.answer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqAnswerAdd {

    private Long questionSid;

    private Long userSid;

    @NotBlank(message = "답변 내용은 필수 입니다.")
    private String content;
}
