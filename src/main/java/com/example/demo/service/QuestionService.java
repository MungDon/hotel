package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.question.ResQuestionList;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;

    @Transactional(readOnly = true)
    public List<ResQuestionList> findQuestion(Long userSid){
        return questionMapper.findQuestion(userSid);
    }


    @Transactional
    public ResponseDTO questionAdd(ReqQuestionAdd req){
        int result = questionMapper.questionAdd(req);
        return CommonUtils.successResponse(result, "질문 등록 성공!", ErrorCode.INSERT_OPERATION_FAILED);
    }


}
