package com.example.demo.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqCustomerStatusUpdate {

    private Long userSid;

    private String statusCode;      // delete or restore
}
