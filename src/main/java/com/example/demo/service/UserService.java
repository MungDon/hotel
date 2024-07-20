package com.example.demo.service;

import com.example.demo.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserChk;
import com.example.demo.dto.response.user.ResUserLogin;
import com.example.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	@Transactional
	public void userAdd(ReqUserAdd add) {
		CommonUtils.throwCustomExceptionIf(!add.getPassword().equals(add.getPassword2()),ErrorCode.PASS_DONT_MATCH);
		ResUserChk emailChk = userMapper.userEmailChk(add.getUser_email()); // mapper 에서 회원 email 정보를 가져온것을
																															// emailChk 라는 이름으로 저장
		ResUserChk nameChk = userMapper.userNameChk(add.getUser_name()); // mapper 에서 회원명 정보를 가져온것을
		if (emailChk != null) { // 입력한 이메일값이 null 이아니라면 = 이미 DB에 존재한다면
			throw new CustomException(ErrorCode.ID_DUPLICATE); // 강제 예외 발생
		}  
		if (nameChk != null) { // 입력한 회원명이 이미 DB에 존재한다면
			throw new CustomException(ErrorCode.USER_NAME_DUPLICATE); // 강제 예외 발생
		}
		userMapper.userAdd(add); // uesrMapper에 유저정보를 전달
	}
	
	@Transactional
	public ResUserLogin userLogin(ReqUserLogin login) {
		ResUserLogin chk =  userMapper.userLogin(login);
		CommonUtils.throwCustomExceptionIf(chk==null, ErrorCode.NO_ACCOUNT);
		return chk;	// 로그인정보 리턴
	}

}
