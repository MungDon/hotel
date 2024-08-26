package com.example.demo.service;

import com.example.demo.mapper.HotelLayoutMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelLayoutService {

    private final HotelLayoutMapper hotelLayoutMapper;
}
