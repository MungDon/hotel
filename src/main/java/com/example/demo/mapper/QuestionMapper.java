package com.example.demo.mapper;

import com.example.demo.dto.request.question.ReqQuestionAdd;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    int questionAdd(ReqQuestionAdd req);
}
