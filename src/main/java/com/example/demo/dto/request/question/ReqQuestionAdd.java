package com.example.demo.dto.request.question;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionAdd {

    private Long userSid;

    @NotBlank(message = "문의 제목은 필수 입니다.")
    private String title;

    @NotBlank(message = "문의 유형 선택은 필수 입니다.")
    private Long questionTypeSid;

    @NotBlank(message = "문의 내용은 필수 입니다.")
    private String content;

    @NotBlank(message = "문의 공개여부 필수 입니다.")
    private String isSecret;

}
