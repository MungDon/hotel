package com.example.demo.mapper;

import com.example.demo.dto.request.payment.ReqPaymentInfoAdd;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    int reservePaymentAdd(ReqPaymentInfoAdd req);
}
