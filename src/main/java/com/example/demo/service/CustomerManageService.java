package com.example.demo.service;

import com.example.demo.dto.Pagination;
import com.example.demo.dto.ResPaging;
import com.example.demo.dto.response.user.CustomerSearchDTO;
import com.example.demo.dto.response.user.ResCustomerManageList;
import com.example.demo.enums.Role;
import com.example.demo.enums.UserDeleteYN;
import com.example.demo.mapper.CustomerManageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerManageService{

    private final CustomerManageMapper customerManageMapper;

    @Transactional(readOnly = true)
    public ResPaging<ResCustomerManageList> customerManageList(CustomerSearchDTO dto){
        int customerCnt = customerManageMapper.customerCnt(dto);
        log.info("카운트" + customerCnt);
        if(customerCnt <= 0){
            return new ResPaging<>(Collections.emptyList(),null);
        }
        Pagination pagination = new Pagination(customerCnt, dto);
        dto.setPagination(pagination);

        List<ResCustomerManageList> customerManageList = customerManageMapper.customerManageList(dto);
        for(ResCustomerManageList res : customerManageList){
            String formattedRole = Role.typeToName(res.getRole());
            String formattedDeleteYn = UserDeleteYN.typeToName(res.getDelete_yn());
            res.setFormattedData(formattedDeleteYn,formattedRole);
        }
        return new ResPaging<>(customerManageList,pagination);
    }

    @Transactional
    public int empDecisionSignup(Long userSid, String roleType){
        return customerManageMapper.empDecisionSignup(userSid,roleType);
    }
}
