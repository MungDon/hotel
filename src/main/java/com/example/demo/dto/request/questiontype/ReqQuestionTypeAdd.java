package com.example.demo.dto.request.questiontype;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqQuestionTypeAdd {

    @NotBlank(message = "문의 타입명은 필수 입니다.")
    private String typeName;
}
