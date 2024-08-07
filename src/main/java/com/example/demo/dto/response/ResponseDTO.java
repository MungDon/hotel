package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseDTO {

    private boolean success;  // 성공 여부
    private HttpStatus status;  // 상태코드
    private String message;     // 응답 메세지
}
