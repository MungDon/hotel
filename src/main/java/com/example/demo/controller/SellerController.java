 package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.seller.ReqSellerAdd;
import com.example.demo.service.SellerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
	
		private final SellerService sellerService;
		
		/*판매자 회원가입 폼 페이지 불러오기*/
		@GetMapping("")
		public String sellerForm() {
			return "sellerjoin";
		}
		
		/*회원가입*/
		@PostMapping("")
		public String sellerAdd(ReqSellerAdd add) {
			sellerService.sellerAdd(add);
			return "redirect:/user/login";
		}
		

}
