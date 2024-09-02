package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IamPortUrl {

    CREATE_TOKEN_REQUEST_URL("https://api.iamport.kr/users/getToken"),
    PAYMENT_CANCEL_REQUEST_URL("https://api.iamport.kr/payments/cancel");

    private final String url;
}
