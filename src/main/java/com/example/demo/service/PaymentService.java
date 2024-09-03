package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.payment.ReqPaymentInfoAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.enums.IamPortUrl;
import com.example.demo.enums.ReservationStatus;
import com.example.demo.mapper.PaymentMapper;
import com.example.demo.util.CommonUtils;
import com.example.demo.util.IamPortKeys;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final ReservationService reservationService;
    private final IamPortKeys iamPortKeys;

    /*결제 내역 저장*/
    @Transactional
    public ResponseDTO reservePaymentAdd(ReqPaymentInfoAdd req){
        reservationService.changeReserveStatus(req.getReserveSid(), ReservationStatus.COMPLETED.getStatus());
        int result = paymentMapper.reservePaymentAdd(req);
        return CommonUtils.successResponse(result,"결제 내역 저장 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }
    @Transactional
    public int refundReserve(String reserveNumber) throws IOException, InterruptedException {
        String cancelToken = createToken(iamPortKeys.getApiKey(),iamPortKeys.getSecretKey());
        HttpClient client = HttpClient.newHttpClient();

        JsonObject json = new JsonObject();
        json.addProperty("merchant_uid", reserveNumber);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(IamPortUrl.PAYMENT_CANCEL_REQUEST_URL.getUrl()))
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization", cancelToken)
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        int result = response.statusCode() == 200 ? 1 :0;
        return result ;
    }

    public String createToken(String apiKey, String secretKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        JsonObject json = new JsonObject();
        json.addProperty("imp_key",apiKey);
        json.addProperty("imp_secret",secretKey);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(IamPortUrl.CREATE_TOKEN_REQUEST_URL.getUrl()))
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        // 응답 본문을 jsonObj 로 변환
        JsonObject jsonResponse = gson.fromJson(response.body(),JsonObject.class);
        String accessToken = jsonResponse
                .getAsJsonObject("response")
                .get("access_token")
                .getAsString();
        CommonUtils.throwRestCustomExceptionIf(CommonUtils.isEmpty(accessToken),ErrorCode.FAIL_AUTHENTICATION);
        return accessToken;
    }

}
