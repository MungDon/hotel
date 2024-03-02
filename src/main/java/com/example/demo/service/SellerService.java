package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.SellerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerService {

		private final SellerMapper sellerMapper;
}
