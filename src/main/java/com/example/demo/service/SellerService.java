package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.seller.ReqSellerAdd;
import com.example.demo.dto.response.seller.ResEmailNameChk;
import com.example.demo.mapper.SellerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerService {

	private final SellerMapper sellerMapper;

	/* 회원가입 */
	@Transactional
	public void sellerAdd(ReqSellerAdd add) {
		ResEmailNameChk emailChk = sellerMapper.sellerEmailChk(add.getUser_email());
		ResEmailNameChk nameChk = sellerMapper.sellerNameChk(add.getUser_name());
		if (emailChk != null) {
			throw new CustomException(ErrorCode.ID_DUPLICATE);
		} 
		if (nameChk != null) {
			throw new CustomException(ErrorCode.USER_NAME_DUPLICATE);
		} 
		if (!add.getPassword().equals(add.getPassword2())) {
			throw new CustomException(ErrorCode.PASS_DONT_MATCH);
		}
		sellerMapper.sellerAdd(add);
	}
}
