package com.example.demo.controller;

import com.example.demo.dto.request.questiontype.ReqQuestionTypeAdd;
import com.example.demo.dto.request.questiontype.ReqQuestionTypeUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.questiontype.ResQuestionTypeList;
import com.example.demo.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question/type")
@RequiredArgsConstructor
public class QuestionTypeController {

    private final QuestionTypeService questionTypeService;

    @GetMapping("")
    public String questionTypeList(Model model){
        List<ResQuestionTypeList> questionTypeList = questionTypeService.findAllQuestionType();
        model.addAttribute("questionTypeList",questionTypeList);
        return "question_type_list";
    }

    @GetMapping("/add")
    public String questionTypeAddForm(){
        return "question_type_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionTypeAdd(@RequestBody List<ReqQuestionTypeAdd> addList){
        ResponseDTO response = questionTypeService.questionTypeAdd(addList);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionTypeUpdate(ReqQuestionTypeUpdate req){
        ResponseDTO response = questionTypeService.questionTypeUpdate(req);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<ResponseDTO> questionTypeDelete(@RequestParam(value = "questionTypeSid")Long questionTypeSid){
        ResponseDTO response = questionTypeService.questionTypeDelete(questionTypeSid);
        return ResponseEntity.ok(response);
    }
}
