package com.example.demo.mapper;

import com.example.demo.dto.request.questiontype.ReqQuestionTypeAdd;
import com.example.demo.dto.request.questiontype.ReqQuestionTypeUpdate;
import com.example.demo.dto.response.questiontype.ResQuestionTypeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionTypeMapper {

    int questionTypeAdd(ReqQuestionTypeAdd req);

    List<ResQuestionTypeList> findAllQuestionType();

    int questionTypeUpdate(ReqQuestionTypeUpdate req);

    int questionTypeDelete(@Param(value = "questionTypeSid")Long questionTypeSid);
}
