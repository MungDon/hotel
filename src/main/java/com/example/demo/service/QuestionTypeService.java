package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.questiontype.ReqQuestionTypeAdd;
import com.example.demo.dto.request.questiontype.ReqQuestionTypeUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.questiontype.ResQuestionTypeList;
import com.example.demo.mapper.QuestionTypeMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTypeService {

    private final QuestionTypeMapper questionTypeMapper;

    @Transactional(readOnly = true)
    public List<ResQuestionTypeList>findAllQuestionType(){
        return questionTypeMapper.findAllQuestionType();
    }

    @Transactional
    public ResponseDTO questionTypeAdd(List<ReqQuestionTypeAdd> addList){
        int result = 0;
        for(ReqQuestionTypeAdd req : addList) {
            result += questionTypeMapper.questionTypeAdd(req);
        }
        return CommonUtils.successResponse(result, "문의 타입 저장 성공" , ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO questionTypeUpdate(ReqQuestionTypeUpdate req){
        int result = questionTypeMapper.questionTypeUpdate(req);
        return CommonUtils.successResponse(result,"문의 타입 수정 성공",ErrorCode.UPDATE_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO questionTypeDelete(Long questionTypeSid){
        int result = questionTypeMapper.questionTypeDelete(questionTypeSid);
        return CommonUtils.successResponse(result,"문의 타입 삭제 성공",ErrorCode.DELETE_OPERATION_FAILED);
    }
}
