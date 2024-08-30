package com.example.demo.dto.request.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPaymentInfoAdd {

    @NotNull(message = "예약 시퀀스 누락")
    private Long reserveSid;      // 예약 시퀀스

    @NotNull(message = "예약자 시퀀스 누락")
    private Long userSid;          //예약자 시퀀스

    @NotBlank(message = "예약 번호 누락")
    private String reserveNumber;  // 예약 번호

    @NotBlank(message = "예약 객실 명 누락")
    private String roomName;    // 예약 객실 명

    @NotNull(message = "객실 가격 누락")
    private int price;          // 객실 가격

    @NotBlank(message = "예약자 핸드폰 번호 누락")
    private String phone;       // 예약자 핸드폰 번호
    
}
