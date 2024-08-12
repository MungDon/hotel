package com.example.demo.dto.response.banner;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResBannerList {

    private Long banner_sid;

    private String banner_name;

    private String banner_url;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;

    private String img_name;
}
