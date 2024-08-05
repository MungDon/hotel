package com.example.demo.controller;

import com.example.demo.dto.request.payment.ReqPaymentInfoAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final IamportClient iamportClient;
    private final PaymentService paymentService;

    @PostMapping("/payment/valid")
    @ResponseBody
    public IamportResponse<Payment> validateIamport(@RequestParam(value = "imp_uid")String imp_uid) throws IamportResponseException, IOException {
        log.info("오긴오니?");
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        return payment;
    }

    @PostMapping("/reserve/payment")
    @ResponseBody
    public ResponseEntity<ResponseDTO> reservePaymentAdd(@ModelAttribute ReqPaymentInfoAdd req){
        log.info("오긴오니?2");
        log.info("유저"+req.getUser_sid());
        log.info("유저"+req.getReserveNumber());
        log.info("유저"+req.getPhone());
        log.info("유저"+req.getPrice());
        log.info("유저"+req.getRoomName());
        ResponseDTO response = paymentService.reservePaymentAdd(req);
        return ResponseEntity.ok(response);
    }


}
