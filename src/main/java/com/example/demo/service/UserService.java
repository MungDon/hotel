package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserInfo;
import com.example.demo.dto.response.user.ResUserLogin;
import com.example.demo.enums.UserAuthStatus;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	
	/*회원가입*/
	@Transactional
	public void userAdd(ReqUserAdd add) {
		CommonUtils.throwCustomExceptionIf(!add.getPassword().equals(add.getPassword2()),ErrorCode.PASS_DONT_MATCH);
		int existNameCnt = userMapper.userNameChk(add.getUser_name()); // mapper 에서 회원명 정보를 가져온것을
		CommonUtils.throwCustomExceptionIf(existNameCnt != 1,ErrorCode.USER_NAME_DUPLICATE);
		add.setAuthStatus(UserAuthStatus.CODE_UNCHECKED.getStatus());
		userMapper.userAdd(add); // uesrMapper에 유저정보를 전달
	}
	
	/*로그인*/
	@Transactional
	public ResUserLogin userLogin(ReqUserLogin login) {
		ResUserLogin chk =  userMapper.userLogin(login);
		CommonUtils.throwCustomExceptionIf(chk==null, ErrorCode.NO_ACCOUNT);
		return chk;	// 로그인정보 리턴
	}
	/*회원정보 찾기*/
	@Transactional(readOnly = true)
	public ResUserInfo findUserByEmail(String email){
		return userMapper.findUserByEmail(email);
	}
	
	/*회원명 중복체크*/
	@Transactional(readOnly = true)
	public void userNameValid(String name) {
		int result = userMapper.userNameChk(name);
		CommonUtils.throwRestCustomExceptionIf(result > 0, ErrorCode.USER_NAME_DUPLICATE);
	}


}
