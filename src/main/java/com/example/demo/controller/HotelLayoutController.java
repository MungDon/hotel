package com.example.demo.controller;

import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.HotelLayoutService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hotel/layout")
@RequiredArgsConstructor
public class HotelLayoutController {

    private final HotelLayoutService hotelLayoutService;
    private final RoomService roomService;

    @GetMapping("")
    public String hotelLayoutList(Model model){
        return "hotel_layout_list";
    }

    @GetMapping("/add")
    public String hotelLayoutAdd(Model model){
        List<ResRoomList> roomList = roomService.simpleRoomList();
        model.addAttribute("roomList",roomList);
        return "hotel_layout_add";
    }
}
