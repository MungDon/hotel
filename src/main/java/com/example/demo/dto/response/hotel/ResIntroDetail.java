package com.example.demo.dto.response.hotel;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResIntroDetail {

    private Long hotel_sid;

    private String user_name;

    private String title;

    private String content;

    private LocalDateTime created_date;

    private LocalDateTime modified_date;

}
