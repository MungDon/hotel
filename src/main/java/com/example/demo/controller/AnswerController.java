package com.example.demo.controller;

import com.example.demo.dto.request.answer.ReqAnswerAdd;
import com.example.demo.dto.request.answer.ReqAnswerUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> answerAdd(@RequestBody @Valid ReqAnswerAdd req){
        ResponseDTO response = answerService.answerAdd(req);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ResponseDTO> answerUpdate(@RequestBody @Valid ReqAnswerUpdate req){
        ResponseDTO response = answerService.answerUpdate(req);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<ResponseDTO> answerDelete(@RequestParam(value = "questionSid")Long questionSid){
        ResponseDTO response = answerService.answerDelete(questionSid);
        return ResponseEntity.ok(response);
    }
}
