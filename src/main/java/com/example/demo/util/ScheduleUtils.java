package com.example.demo.util;

import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleUtils {

    private final HotelService hotelService;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteImageWithoutHotelSid(){
        hotelService.deleteImageWithoutHotelSid();
    }
}
