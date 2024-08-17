package com.example.demo.dto.request.question;

import com.example.demo.dto.Search;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSearchDTO extends Search {

    private Long questionType;   // 문의 타입
}
