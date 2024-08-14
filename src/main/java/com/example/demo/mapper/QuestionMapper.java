package com.example.demo.mapper;

import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.response.question.ResQuestionList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<ResQuestionList>findQuestion(@Param(value = "userSid")Long userSid);

    int questionAdd(ReqQuestionAdd req);
}
