package com.example.demo.dto.request.question;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionUpdate {

    private Long questionSid;

    @NotBlank(message = "제목은 필수 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입니다.")
    private String content;
}
