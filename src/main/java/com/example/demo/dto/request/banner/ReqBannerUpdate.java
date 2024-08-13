package com.example.demo.dto.request.banner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReqBannerUpdate {

    private Long banner_sid;

    private String banner_name;

    private String banner_url;

    private String current_img;

    private MultipartFile banner_img;
}
