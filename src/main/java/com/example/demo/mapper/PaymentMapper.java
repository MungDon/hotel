package com.example.demo.mapper;

import com.example.demo.dto.request.payment.ReqPaymentInfoAdd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {

    int reservePaymentAdd(ReqPaymentInfoAdd req);

    void deletePayment(@Param(value = "reserveSid")Long reserveSid);
}
