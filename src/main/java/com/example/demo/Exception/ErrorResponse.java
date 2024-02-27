package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ErrorResponse {

		private HttpStatus status;
		private String  message;
		
		private ErrorResponse(final ErrorCode code) {
	        this.message = code.getMessage();
	        this.status = code.getStatus();
	    }

	    public static ErrorResponse of(final ErrorCode code) {
	        return new ErrorResponse(code);
	    }

	    


		
}
