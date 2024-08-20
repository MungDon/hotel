package com.example.demo.enums;

import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.CustomerManageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserManageCode {

    DELETE("delete")
        {
        @Override
        public ResponseDTO customerManage(CustomerManageService customerManageService, Long userSid) {
            return customerManageService.customerDelete(userSid);
        }
    },
    RESTORE ("restore") {
        @Override
        public ResponseDTO customerManage(CustomerManageService customerManageService, Long userSid) {
            return customerManageService.customerRestore(userSid);
        }


    };

    private final String code;
    public abstract ResponseDTO customerManage(CustomerManageService customerManageService, Long userSid);

    public static ResponseDTO findCodeByStatusCode(CustomerManageService customerManageService,String statusCode, Long userSid) {
        for (UserManageCode codeEnum : UserManageCode.values()) {
            if (codeEnum.getCode().equals(statusCode)) {
                return codeEnum.customerManage(customerManageService, userSid);
            }
        }
        return ResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("일치하는 코드 없음")
                .success(false)
                .build();
    }
}

