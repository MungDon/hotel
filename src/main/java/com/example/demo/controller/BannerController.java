package com.example.demo.controller;

import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.banner.ResBannerList;
import com.example.demo.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;
    @GetMapping("")
    public String bannerList(Model model) {
        List<ResBannerList> bannerList = bannerService.findAllBanner();
        model.addAttribute("bannerList",bannerList);
        return "banner_list";
    }

    @GetMapping("/add")
    public String bannerAddForm() {
        return "banner_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity bannerAdd(@RequestParam Map<String, String> params, @RequestParam Map<String, MultipartFile> files) throws IOException {
        ResponseDTO response = bannerService.bannerAdd(params,files);
        return ResponseEntity.ok(response);
    }
}