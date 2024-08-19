package com.example.demo.mapper;

import com.example.demo.dto.request.answer.ReqAnswerAdd;
import com.example.demo.dto.request.answer.ReqAnswerUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AnswerMapper {

    int answerAdd(ReqAnswerAdd req);

    int answerUpdate(ReqAnswerUpdate req);

    int answerDelete(@Param(value = "questionSid")Long questionSid);
}
