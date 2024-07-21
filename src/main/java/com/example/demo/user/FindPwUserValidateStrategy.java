package com.example.demo.user;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.response.user.ResUserInfo;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("findPw")
@RequiredArgsConstructor
public class FindPwUserValidateStrategy implements UserValidateStrategy {

    private final UserService userService;
    @Override
    public void memberChk(String email) {
        ResUserInfo userInfo = userService.findUserByEmail(email);
        CommonUtils.throwRestCustomExceptionIf(userInfo==null, ErrorCode.NO_ACCOUNT);
    }
}
