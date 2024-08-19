package com.example.demo.dto.response.user;

import com.example.demo.dto.Search;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchDTO extends Search {

    private String role;        // 유저 권한

    private String deleteYN;    // 탈퇴 여부
}
