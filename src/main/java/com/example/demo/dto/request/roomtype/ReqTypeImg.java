package com.example.demo.dto.request.roomtype;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqTypeImg {

    private Long roomTypeSid;

    private String originalName;

    private String imgName;

    private String extension;

}
