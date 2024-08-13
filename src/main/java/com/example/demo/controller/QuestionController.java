package com.example.demo.controller;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.QuestionService;
import com.example.demo.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /*고객문의 목록*/
    @GetMapping("")
    public String questionList(Model model){
        return "question_list";
    }

    /*고객 문의 등록 폼*/
    @GetMapping("/add")
    public String questionAddForm(HttpServletRequest request){
        Long userSid = CommonUtils.getUserSid(request);
        CommonUtils.throwCustomExceptionIf(CommonUtils.isEmpty(userSid), ErrorCode.AUTHENTICATION_REQUIRED);
        return "question_add";
    }

    /*고객 문의 등록*/
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionAdd(ReqQuestionAdd req){
        ResponseDTO response = questionService.questionAdd(req);
        return ResponseEntity.ok(response);
    }
}
