package com.example.demo.mapper;

import com.example.demo.dto.response.user.CustomerSearchDTO;
import com.example.demo.dto.response.user.ResCustomerManageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerManageMapper {

    int customerCnt(CustomerSearchDTO dto);

    List<ResCustomerManageList> customerManageList(CustomerSearchDTO dto);

    int empDecisionSignup(@Param(value = "userSid")Long userSid,@Param(value = "roleType")String roleType);
}
