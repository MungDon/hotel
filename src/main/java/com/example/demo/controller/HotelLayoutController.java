package com.example.demo.controller;

import com.example.demo.dto.request.layout.ReqLayoutAdd;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.room.ResRoomList;
import com.example.demo.service.HotelLayoutService;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String hotelLayoutAddForm(Model model){
        List<ResRoomList> roomList = roomService.simpleRoomList();
        model.addAttribute("roomList",roomList);
        return "hotel_layout_add";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<ResponseDTO> hotelLayoutAdd(@RequestBody List<ReqLayoutAdd> addList){
        ResponseDTO response = hotelLayoutService.hotelLayoutAdd(addList);
        return ResponseEntity.ok(response);
    }
}
