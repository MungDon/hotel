package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthStatus {

    CODE_CHECKED("인증됨"),
    CODE_UNCHECKED("인증안됨");

    private final String status;
}
