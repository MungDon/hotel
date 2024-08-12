package com.example.demo.dto.request.banner;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class ReqBannerAdd {

    private Long banner_sid;

    private String banner_name;

    private String banner_url;

    private MultipartFile banner_img;
}
