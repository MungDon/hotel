package com.example.demo.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestCustomException extends RuntimeException{

	private final ErrorCode errorCode;
}
