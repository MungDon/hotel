package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.payment.ReqPaymentInfoAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.enums.ReservationType;
import com.example.demo.mapper.PaymentMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final ReservationService reservationService;

    @Transactional
    public ResponseDTO reservePaymentAdd(ReqPaymentInfoAdd req){

        reservationService.changeReserveStatus(req.getUser_sid(),ReservationType.COMPLETED.getStatus());
        int result = paymentMapper.reservePaymentAdd(req);

        return CommonUtils.successResponse(result,"결제 내역 저장 성공", ErrorCode.INSERT_OPERATION_FAILED);

    }
}
