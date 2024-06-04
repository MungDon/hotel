package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
}
