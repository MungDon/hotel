package com.example.demo.controller;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.question.ResQuestionList;
import com.example.demo.dto.response.questiontype.ResQuestionTypeList;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuestionTypeService;
import com.example.demo.util.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionTypeService questionTypeService;

    /*고객문의 목록*/
    @GetMapping("")
    public String questionList(Model model,HttpServletRequest request){
        Long userSid = CommonUtils.getUserSid(request);
        CommonUtils.throwCustomExceptionIf(CommonUtils.isEmpty(userSid), ErrorCode.AUTHENTICATION_REQUIRED);
        List<ResQuestionList> questionList = questionService.findQuestion(userSid);
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    /*고객 문의 등록 폼*/
    @GetMapping("/add")
    public String questionAddForm(Model model){
        List<ResQuestionTypeList> questionTypeList = questionTypeService.findAllQuestionType();
        model.addAttribute("questionTypeList",questionTypeList);
        return "question_add";
    }

    /*고객 문의 등록*/
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionAdd(@RequestBody @Valid ReqQuestionAdd req){
        ResponseDTO response = questionService.questionAdd(req);
        return ResponseEntity.ok(response);
    }
}
