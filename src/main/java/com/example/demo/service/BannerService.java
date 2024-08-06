package com.example.demo.service;

import com.example.demo.mapper.BannerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerMapper bannerMapper;

}
