package com.example.demo.dto.request.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPaymentInfoAdd {

    private Long user_sid;      // 예약자 시퀀스
    
    private String reserveNumber;  // 예약 번호
    
    private String roomName;    // 예약 객실 명

    private int price;          // 객실 가격

    private String phone;       // 예약자 핸드폰 번호
    
}
