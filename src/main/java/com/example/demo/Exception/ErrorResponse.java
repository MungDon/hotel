package com.example.demo.Exception;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponseException;

import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy.FirstCharBasedValidator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
		
		private int status;
		private String  message;
		private List<FieldError> errorss;
		
		
		public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
			return new ErrorResponse(code, FieldError.of(bindingResult));
		}
		
		public ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
			 this.message = code.getMessage() + "\n";
			 for(FieldError error : errors) {
				 this.message += error.getMessage()+"\n";
			 }
			 this.status = code.getStatus().value();
			 this.errorss = errors;
		} 




		//Valid 에러감지
		@Getter
		@NoArgsConstructor(access = AccessLevel.PROTECTED)
		public static class FieldError{
			private String field;
			private String value;
			private String message;
			
			private FieldError(final String field, final String value, final String message) {
				this.field = field;
				this.value = value;
				this.message = message;
			}
			
			private static List<FieldError> of(final BindingResult bindingResult){
				final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
				return fieldErrors.stream()
						.map(error -> new FieldError(
								error.getField(),
								error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
								error.getDefaultMessage()))
						.collect(Collectors.toList());
								
				
			}
		}


}
