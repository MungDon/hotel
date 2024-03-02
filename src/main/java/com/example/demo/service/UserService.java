package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserChk;
import com.example.demo.dto.response.user.ResUserLogin;
import com.example.demo.enums.Role;
import com.example.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	@Transactional
	public void userAdd(ReqUserAdd add) {
		ResUserChk emailChk = userMapper.userEmailChk(add.getUser_email()); // mapper 에서 회원 email 정보를 가져온것을
																															// emailChk 라는 이름으로 저장
		ResUserChk nameChk = userMapper.userNameChk(add.getUser_name()); // mapper 에서 회원명 정보를 가져온것을
		if (emailChk != null) { // 입력한 이메일값이 null 이아니라면 = 이미 DB에 존재한다면
			throw new CustomException(ErrorCode.ID_DUPLICATE); // 강제 예외 발생
		} else if (nameChk != null) { // 입력한 회원명이 이미 DB에 존재한다면
			throw new CustomException(ErrorCode.PASSWORD_DUPLICATE); // 강제 예외 발생
		}
		if (!add.getPassword().equals(add.getPassword2())) { // 첫번째 비밀번호입력과 재확인 비밀번호 입력이 일치하지않다면
			throw new CustomException(ErrorCode.PASS_DONT_MATCH); // 강제 예외 발생
		}
		userMapper.userAdd(add); // uesrMapper에 유저정보를 전달
	}
	
	@Transactional
	public ResUserLogin userLogin(ReqUserLogin login) {
		ResUserLogin chk =  userMapper.userLogin(login);
		if(chk == null) {
			throw new CustomException(ErrorCode.NO_ID); //입력한 로그인 정보가 없으면 예외발생
		} else if(!login.getPassword().equals(chk.getPassword())) {
			throw new CustomException(ErrorCode.NO_PASSWORD);//입력한 비밀번호와 DB의 저장된 비밀번호다 다를시 예외발생
		}
		System.out.println(chk.getRole());
		Role.fromString(chk.getRole(), Role.USER);
		return chk;	// 로그인정보 리턴
	}

}
