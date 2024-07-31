package com.example.demo.controller;

import com.example.demo.util.CommonUtils;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final IamportClient iamportClient;
    private final PaymentService paymentService;

    @PostMapping("/payment/valid/{imp_uid}")
    @ResponseBody
    public IamportResponse<Payment> validateIamport(@PathVariable(value = "imp_uid")String imp_uid) throws IamportResponseException, IOException {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        return payment;
    }

    @PostMapping("/reserve/payment")
    @ResponseBody
    public ResponseEntity<String> reservePayment(HttpServletRequest request){
        Long user_sid = CommonUtils.getUserSid(request);
        return ResponseEntity.ok("ok");
    }


}
