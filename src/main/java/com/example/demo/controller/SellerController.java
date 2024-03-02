 package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.service.SellerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SellerController {
	
		private final SellerService sellerService;
}
