package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.request.user.ReqUserAdd;
import com.example.demo.dto.request.user.ReqUserLogin;
import com.example.demo.dto.response.user.ResUserLogin;

@Mapper
public interface UserMapper {
		/*회원가입*/
		 void userAdd(ReqUserAdd add); // 회원가입 정보를 DB에서 가져온다
		 

		 /*회원명 중복 체크*/
		 int userNameChk(@Param(value = "user_name")String user_name);

		 /*로그인*/
		 ResUserLogin userLogin(ReqUserLogin login);


}
