package com.example.demo.controller;

import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/list")
    public String bannerList(Model model){
        return "banner_list";
    }

    @GetMapping("/add")
    public String bannerAddForm(){
        return "banner_add";
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity bannerAdd(){
        ResponseDTO response = bannerService.bannerAdd();
        return ResponseEntity.ok(response);
    }

}
