package com.example.demo.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqEmpApproval {

    private Long userSid;       // 유저 시퀀스

    private String decisionMessage; // 승인/ 거절 메세지
}
