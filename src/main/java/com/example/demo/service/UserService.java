package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.response.user.ResUserChk;
import com.example.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;

	public void userAdd(ReqUserAdd add) {
		ResUserChk emailChk = userMapper.userEmailChk(add.getUser_email()); 	// mapper 에서 유저 email 정보를 가져온것을 
																															//emailChk 라는 이름으로 저장
		ResUserChk nameChk = userMapper.userNameChk(add.getUser_name());
		try {
			if (emailChk != null) { // info,즉 입력한 이메일값이 null 이아니라면 = 이미 DB에 존재한다면
				throw new Exception("이메일이 중복되었습니다"); // 강제 예외 발생
			}
			else if (nameChk != null) {
				throw new Exception("닉네임이 중복되었습니다");
			}
			userMapper.userAdd(add); // uesrMapper에 유저정보를 전달
		} catch (Exception e) {
			System.out.println("경고 : " + e.getMessage()); // 예외 발생한걸 잡아와서 콘솔에 메시지를 띄움
		}

	}
}
