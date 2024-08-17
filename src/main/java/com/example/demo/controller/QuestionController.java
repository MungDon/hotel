package com.example.demo.controller;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.request.question.QuestionSearchDTO;
import com.example.demo.dto.request.question.ReqQuestionAdd;
import com.example.demo.dto.request.question.ReqQuestionUpdate;
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

    /*고객 문의 수정*/
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionUpdate(@RequestBody @Valid ReqQuestionUpdate req){
        ResponseDTO response = questionService.questionUpdate(req);
        return ResponseEntity.ok(response);
    }
    
    /*고객 문의 삭제*/
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionDelete(@RequestParam(value = "questionSid")Long questionSid){
        ResponseDTO response = questionService.questionDelete(questionSid);
        return ResponseEntity.ok(response);
    }

    /*고객 문의 목록(관리자)*/
    @GetMapping("/management")
    public String questionManageList(Model model, @ModelAttribute("search") QuestionSearchDTO dto){
        ResPaging<ResQuestionList> questionList = questionService.questionManageList(dto);
        List<ResQuestionTypeList> questionTypeList = questionTypeService.findAllQuestionType();
        model.addAttribute("questionTypeList",questionTypeList);
        model.addAttribute("questionList",questionList);
        return "question_manage_list";
    }
}
