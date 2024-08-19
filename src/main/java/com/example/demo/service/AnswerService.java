package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.answer.ReqAnswerAdd;
import com.example.demo.dto.request.answer.ReqAnswerUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.enums.QuestionStatus;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Transactional
    public ResponseDTO answerAdd(ReqAnswerAdd req){
        int result = 0;
        int insertResult = answerMapper.answerAdd(req);
        if(insertResult > 0){
            result =  questionMapper.changeQuestionStatus(req.getQuestionSid(),QuestionStatus.ANSWER_COMPLETE.getStatus());
        }
        return CommonUtils.successResponse(result,"답변 등록 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO answerUpdate(ReqAnswerUpdate req){
        int result = answerMapper.answerUpdate(req);
        return CommonUtils.successResponse(result, "답변 수정 성공",ErrorCode.UPDATE_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO answerDelete(Long questionSid){
        int result = 0;
        int deleteResult = answerMapper.answerDelete(questionSid);
        if(deleteResult>0){
            result =  questionMapper.changeQuestionStatus(questionSid,QuestionStatus.ANSWER_WAITING.getStatus());
        }
        return CommonUtils.successResponse(result,"답변 삭제 성공", ErrorCode.DELETE_OPERATION_FAILED);

    }
}
