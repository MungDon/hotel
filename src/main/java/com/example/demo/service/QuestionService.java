package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.Pagination;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.question.QuestionSearchDTO;
import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.request.question.ReqQuestionUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.question.ResQuestionList;
import com.example.demo.enums.QuestionStatus;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        req.bindingQuestionStatus(QuestionStatus.ANSWER_WAITING.getStatus());
        int result = questionMapper.questionAdd(req);
        return CommonUtils.successResponse(result, "질문 등록 성공!", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO questionUpdate(ReqQuestionUpdate req){
        int result = questionMapper.questionUpdate(req);
        return CommonUtils.successResponse(result, "문의 수정 성공",ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO questionDelete(Long questionSid){
        int result = questionMapper.questionDelete(questionSid);
        return CommonUtils.successResponse(result, "문의 삭제 성공", ErrorCode.DELETE_OPERATION_FAILED);
    }

    @Transactional(readOnly = true)
    public ResPaging<ResQuestionList> questionManageList(QuestionSearchDTO dto){
        // 검색 조건에 맞는 데이터가 없을 경우 빈 리스트, null 반환
        int listCount = questionMapper.questionListCnt(dto);
        if(listCount < 1){
            return new ResPaging<>(Collections.emptyList(),null);
        }
        // 페이징 객체 생성과 동시에 페이징 계산
        Pagination pagination = new Pagination(listCount,dto);
        dto.setPagination(pagination);
        
        // 계산완료 후 해당 변수들로 offset 정해서 데이터 가져옴
        List<ResQuestionList> questionManageList = questionMapper.questionManageList(dto);

        // 응답 객체 안에 리스트와 페이징 객체 담음
        return new ResPaging<>(questionManageList, pagination);

    }

}
