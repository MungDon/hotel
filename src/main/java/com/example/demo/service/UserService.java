package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.response.user.ResUserChk;
import com.example.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;		//@RequirdArgsConstructor 로인해 final로 지정한 필드의 생성자를 자동생성함 (의존성 주입 중 생성자 주입 방식)
	
	public void userAdd(ReqUserAdd add) {
		ResUserChk emailChk = userMapper.userEmailChk(add.getUser_email()); 	// mapper 에서 회원 email 정보를 가져온것을 
																															// emailChk 라는 이름으로 저장
		ResUserChk nameChk = userMapper.userNameChk(add.getUser_name());	// mapper 에서 회원명 정보를 가져온것을 
		try {																												// nameChk 라는 이름으로 저장
			if (emailChk != null) { //  입력한 이메일값이 null 이아니라면 = 이미 DB에 존재한다면
				throw new Exception("이메일이 중복되었습니다"); // 강제 예외 발생
			}
			else if (nameChk != null) {	// 입력한 회원명이 이미 DB에 존재한다면
				throw new Exception("닉네임이 중복되었습니다");	// 강제 예외 발생
			}
			if(!add.getPassword().equals(add.getPassword2())) {	// 첫번째 비밀번호입력과 재확인 비밀번호 입력이 일치하지않다면
				throw new Exception("비밀번호가 서로 일치 하지 않습니다."); // 강제 예외 발생
			}
			userMapper.userAdd(add); // uesrMapper에 유저정보를 전달
		} catch (Exception e) {	// 모든 예외를 받을 수 있다
			System.out.println("경고 : " + e.getMessage()); // 예외 발생한걸 잡아와서 콘솔에 메시지를 띄움
		}

	}
}
