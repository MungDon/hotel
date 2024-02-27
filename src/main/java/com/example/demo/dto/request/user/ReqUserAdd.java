package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqUserAdd extends ReqUserLogin{		
	@ NotBlank(message = "회원명을 입력해주세요! (공백 불가능)")
	@Pattern(regexp = "^[a-zA-Z0-9가-힣_-]{2,6}$",message = "2~6자리여야하고 숫자 한.영어 가능, 특수기호는 -, _만 사용가능합니다")
	private String user_name;			// 유저명
	
	@NotBlank(message = "비밀번호 재확인을 적지않았어요~!")
	private String password2;			// 유저 비밀번호 재확인
}
