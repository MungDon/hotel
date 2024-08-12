package com.example.demo.dto.request.banner;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReqBannerImg{

    private Long banner_sid;

    private String original_name;

    private String img_name;

    private String extension;
}
