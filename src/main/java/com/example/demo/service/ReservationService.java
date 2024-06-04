package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationMapper reservationMapper; 
}
