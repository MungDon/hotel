package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.request.seller.ReqSellerAdd;
import com.example.demo.dto.response.seller.ResEmailNameChk;

@Mapper
public interface SellerMapper {
	/*회원가입*/
	void sellerAdd(ReqSellerAdd add);
	
	/*이메일 체크*/
	ResEmailNameChk sellerEmailChk(@Param(value="user_email")String user_eamil);
	
	/*닉네임 체크*/
	ResEmailNameChk	 sellerNameChk(@Param(value="user_name")String user_name);
}
