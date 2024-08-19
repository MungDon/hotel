package com.example.demo.user;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.enums.Role;
import com.example.demo.service.CustomerManageService;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("approve")
@RequiredArgsConstructor
public class empApproveSignup implements EmpDecisionSignup{

    private final CustomerManageService customerManageService;
    @Override
    public ResponseDTO empDecisionSignup(Long userSid) {
        int result = customerManageService.empDecisionSignup(userSid, Role.STAFF.getType());
        return CommonUtils.successResponse(result,"가입 거절 성공", ErrorCode.UPDATE_OPERATION_FAILED);
    }
}
