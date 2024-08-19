package com.example.demo.mapper;

import com.example.demo.dto.response.user.CustomerSearchDTO;
import com.example.demo.dto.response.user.ResCustomerManageList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerManageMapper {

    int customerCnt(CustomerSearchDTO dto);

    List<ResCustomerManageList> customerManageList(CustomerSearchDTO dto);
}
